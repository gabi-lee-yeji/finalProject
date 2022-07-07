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

public class AnalyticsService {
	private static final String APPLICATION_NAME = "Hello Analytics Reporting";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String KEY_FILE_LOCATION = "C:\\Users\\yejig\\OneDrive\\Desktop\\JAVA\\finalproject-tj01-56d2d06db588.json";
	private static final String VIEW_ID = "270372661";
	
	private static AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {

	    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    GoogleCredential credential = GoogleCredential
	        .fromStream(new FileInputStream(KEY_FILE_LOCATION))
	        .createScoped(AnalyticsReportingScopes.all());

	    // Construct the Analytics Reporting service object.
	    return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
	        .setApplicationName(APPLICATION_NAME).build();
	}
	
	private static GetReportsResponse getCustomReport(String startDate, String endDate, String metric ) throws IOException {
		
		// Create the DateRange object.
		DateRange dateRange = new DateRange();
	    dateRange.setStartDate(startDate);
	    dateRange.setEndDate(endDate);

	    // Create the Metrics object.
	    Metric sessions = new Metric().setExpression("ga:"+metric);

	    //Dimension pageTitle = new Dimension().setName("ga:pageTitle");

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
	    AnalyticsReporting service;
	    GetReportsResponse response = null;
		try {
			service = initializeAnalyticsReporting();
			response = service.reports().batchGet(getReport).execute();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    // Return the response.
	    return response;
	}
	
	public Map<String, String>  getStatsMap(String startDate, String endDate, String metric) throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		
		GetReportsResponse response = getCustomReport(startDate, endDate, metric);
		
		for (Report report: response.getReports()) {
			ColumnHeader header = report.getColumnHeader();
		    //List<String> dimensionHeaders = header.getDimensions();
		    List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
		    List<ReportRow> rows = report.getData().getRows();

		    if (rows == null) {
		    	System.out.println("No data found for " + VIEW_ID);
		        return map;
		    }

            for (ReportRow row: rows) {
            	//List<String> dimensions = row.getDimensions();
		        List<DateRangeValues> metrics = row.getMetrics();

//		        for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
//		          System.out.println(dimensionHeaders.get(i) + ": " + dimensions.get(i));
//		       	}

		        for (int j = 0; j < metrics.size(); j++) {
		          System.out.print("Date Range (" + j + "): ");
		          DateRangeValues values = metrics.get(j);
		          for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
		            System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
		          }
		        }
		      }
		}
		
		return map;
	}
}