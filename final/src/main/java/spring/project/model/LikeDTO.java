package spring.project.model;

import lombok.Data;

@Data
public class LikeDTO {
    
   private String cnum;   
   private String memid;
    
   public LikeDTO(String cnum, String memid) {
     super();
     this.cnum = cnum;
     this.memid = memid;
    }
   
   public LikeDTO() {
     super();
   }
}