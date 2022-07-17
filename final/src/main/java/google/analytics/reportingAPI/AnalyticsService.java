package google.analytics.reportingAPI;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;

import com.google.api.services.analyticsreporting.v4.model.ColumnHeader;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.MetricHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;

@Component
public class AnalyticsService {
	private static final String APPLICATION_NAME = "Hello Analytics Reporting";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String KEY_FILE_LOCATION = "PRIVATE";
	private static final String VIEW_ID = "VIEW_ID";
	
	private static AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {

	    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    GoogleCredential credential = GoogleCredential
	        .fromStream(new FileInputStream(KEY_FILE_LOCATION))
	        .createScoped(AnalyticsReportingScopes.all());

	    // Construct the Analytics Reporting service object.
	    return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
	        .setApplicationName(APPLICATION_NAME).build();
	}
	
	private static GetReportsResponse getCustomReport(AnalyticsReporting service, String startDate, String endDate) throws IOException {
		
		// Create the DateRange object.
		DateRange dateRange = new DateRange();
	    dateRange.setStartDate(startDate);
	    dateRange.setEndDate(endDate);

	    // Create the Metrics object.
	    Metric sessions = new Metric().setExpression("ga:users");

	    // Create the ReportRequest object.
	    ReportRequest request = new ReportRequest()
	    			.setViewId(VIEW_ID)
    				.setDateRanges(Arrays.asList(dateRange))
    				.setMetrics(Arrays.asList(sessions));

	    ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
	    requests.add(request);

	    // Create the GetReportsRequest object.
	    GetReportsRequest getReport = new GetReportsRequest()
	    								.setReportRequests(requests);

	    // Call the batchGet method.
	    GetReportsResponse response = service.reports().batchGet(getReport).execute();
	    
	    // Return the response.
	    return response;
	}
	
	public int getUsersStats(String startDate, String endDate) throws IOException{
		int users = 0;
		
		try {
			AnalyticsReporting service = initializeAnalyticsReporting();
			GetReportsResponse response = getCustomReport(service, startDate, endDate);
		
			for (Report report: response.getReports()) {
				ColumnHeader header = report.getColumnHeader();
			    //List<String> dimensionHeaders = header.getDimensions();
			    List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
			    List<ReportRow> rows = report.getData().getRows();
	
			    if (rows == null) {
			    	System.out.println("No data found for " + VIEW_ID);
			    	return users;
			    }
	
	            for (ReportRow row: rows) {
	            	//List<String> dimensions = row.getDimensions();
			        List<DateRangeValues> metrics = row.getMetrics();
//			        for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
//	                    System.out.println(dimensionHeaders.get(i) + ": " + dimensions.get(i));
//	                }

			        for (int j = 0; j < metrics.size(); j++) {
			          System.out.print("Date Range (" + j + "): ");
			          DateRangeValues values = metrics.get(j);
			          for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
			            System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
			            users += Integer.parseInt(values.getValues().get(k));
			          }
			        }
			      }
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return users;
	}
}
