package spring.project.model;

import lombok.Data;

@Data
public class LikeDTO {
    
   private String cnum;   
   private String memid;
    
   //생성자 오버로딩
      public LikeDTO(String cnum, String memid) {
         super();
         this.cnum = cnum;
         this.memid = memid;
      }
      public LikeDTO() {
         super();
      }
}