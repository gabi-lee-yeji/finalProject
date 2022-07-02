package spring.project.model;


import lombok.Data;

@Data
public class CertiInfoDTO implements CertiAccessible{
	private String cnum; //ÀÚ°ÝÁõ °íÀ¯¹øÈ£ (PK)
	private String cname;
	private String category; // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿?, ï¿½Î°ï¿½, ï¿½ï¿½ï¿½ï¿½
	private String clevel; // ï¿½Ú°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿?
	
	private String company; //ï¿½ï¿½ï¿½ï¿½Ã³/ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½(ï¿½Ú°Ý°ï¿½ï¿½ï¿½ï¿½ï¿½)
	private String website; // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æ®
	
	private String requirement; //ï¿½ï¿½ï¿½ï¿½ï¿½Ú°ï¿½
	
	private String cmethod; //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿? eg) ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ / ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½...
	private String subject; // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	
	private String cutline; // ï¿½Õ°Ý±ï¿½ï¿½ï¿½
	private String cinfo; // ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
	
	private String cjob; // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
	
	private String expiry;
	private String status; //½ÃÇàÇöÈ² 
	
	private String price; //ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ã·ï¿½
	
	private String ncs_cat;
	
	private String notice; //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	
	private String registDate; //DBï¿½ï¿½ ï¿½ï¿½Ïµï¿? ï¿½ï¿½Â¥	
}
