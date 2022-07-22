#큐넷에서 연도/시험회차별 시행하는 과목 리스트 크롤링
#기술사, 기능장, 기사/산업기사, 기능사 별로 페이지 구성과 테이블 id명이 달라서
#각각 for문을 돌려서 처리했습니다 

rm(list=ls())

library(httr)
library(RSelenium)
library(Rserve)
library(stringr)

remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
remDr$navigate("https://www.q-net.or.kr/crf021.do?id=crf02101&gSite=Q&gId=&scheType=01")

mat <- matrix(nrow=0, ncol=4)
colnames(mat) <- c("연도", "회차","분류", "자격명")

##################################기술사

nodeSubject <- remDr$findElements("css", "#\\30 1")
sapply(nodeSubject, function(x){x$clickElement()}) #클릭

for (j in 1:100){
  node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > th"))
  if(length(node) == 0) #더이상 값을 받아올수 없는경우 
    break
  
  date <- sapply(node,function(x){x$getElementText()})
  date_vec <- unlist(date)
  date2 <- str_split(date_vec, "/\\n") #값 불러온게 "2022년/제1회" 형식이라 "/+엔터" 로 값 분리
  date2 <- unlist(date2)
  date2 <- gsub("[^0-9]", "" , date2) #숫자만 분리(cyear, cround 추출완료)
  
  #print(date2)
  
  
  node <- remDr$findElements("css",paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > td"))
  content <- sapply(node, function(x){x$getElementText()})
  con_vec <- unlist(content)
  
  a_vec <- unlist(str_split(con_vec, "\n")) # 줄바꿈(\n)으로 값 분리
  b_vec <- str_split(a_vec,": ") # "ncs명: 자격증명,자격증명.." 되어있는 값을 ": "으로 분리
  c_vec <- unlist(b_vec)
  d_vec <- unlist(str_split(c_vec[seq(2,length(c_vec),2)],",")) #": "으로 분리한것을 뒤에 자격증명만 분리
  #matrix에 cyear, cround, "기술사"(clevel), 자격증명(cname) 으로 저장
  #rbind로 계속 row를 추가한다 
  mat <- rbind(mat, cbind( rep(date2[1], length(d_vec)) , rep(date2[2], length(d_vec)),rep("기술사", length(d_vec)), d_vec))
  
  
  #e_vec <- c(e_vec, d_vec)
  #print(e_vec)
}

#나머지는 기술사와 대동소이합니다
#기능장의 경우 테이블구조(rowspan 있는경우) if문으로 경우 나눠서 처리한게 다르고
#기사/산업기사의 경우 css table명이 다른것과, 기사/산업기사 문자열을 따로 추출해서 처리한것,
#기능사는 컬럼개수가 다르고 시험마다 필기+실기 보는것/ 실기만 보는것/ 특성화고 필기면제(이건 가져올 필요 없음)
#경우를 나눠서 처리 

#############################기능장

nodeSubject <- remDr$findElements("css", "#\\30 2")
sapply(nodeSubject, function(x){x$clickElement()}) #클릭

j <- 1
u <- 0
repeat {
  node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > th"))
  
  if(length(node) == 0){
    
    node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > td:nth-child(1)"))
    
    if(length(node) == 0){
      break
    }
    content <- sapply(node, function(x){x$getElementText()})
    con_vec <- unlist(content)
    
    a_vec <- unlist(str_split(con_vec, "\n"))
    b_vec <- str_split(a_vec,": ")
    c_vec <- unlist(b_vec)
    d_vec <- unlist(str_split(c_vec[seq(2,length(c_vec),2)],","))
    #print(d_vec)
    mat <- rbind(mat, cbind( rep(date2[1], length(d_vec)) , rep(date2[2], length(d_vec)),rep("기능장", length(d_vec)), d_vec))
    
    j<-j+1
    u<-0
  }else{
    
    if(u == 1){
      node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > td:nth-child(2)"))
      
      content <- sapply(node, function(x){x$getElementText()})
      con_vec <- unlist(content)
      
      a_vec <- unlist(str_split(con_vec, "\n"))
      b_vec <- str_split(a_vec,": ")
      c_vec <- unlist(b_vec)
      d_vec <- unlist(str_split(c_vec[seq(2,length(c_vec),2)],","))
      #print(d_vec)
      
      mat <- rbind(mat, cbind( rep(date2[1], length(d_vec)) , rep(date2[2], length(d_vec)),rep("기능장", length(d_vec)), d_vec))
      
      j<-j+1
    }else{
      
      date <- sapply(node,function(x){x$getElementText()})
      date_vec <- unlist(date)
      date2 <- str_split(date_vec, "/\\n")
      date2 <- unlist(date2)
      date2 <- gsub("[^0-9]", "" , date2)
      
      #print(date2)
      u <- 1
    }
  }
}


#############################기사, 산업기사



nodeSubject <- remDr$findElements("css", "#\\30 3")
sapply(nodeSubject, function(x){x$clickElement()}) #클릭

for(j in 1:1000){
  node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb30 > table > tbody > tr:nth-child(",j,") > th"))
  
  if(length(node) == 0){
    
    node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb30 > table > tbody > tr:nth-child(",j,") > td:nth-child(1)"))
    
    if(length(node) == 0){
      break
    }
    content <- sapply(node, function(x){x$getElementText()})
    con_vec <- unlist(content)
    
    a_vec <- unlist(str_split(con_vec, "\n"))
    b_vec <- str_split(a_vec,": ")
    c_vec <- unlist(b_vec)
    d_vec <- unlist(str_split(c_vec[seq(2,length(c_vec),2)],","))
    #print(d_vec)
    mat <- rbind(mat, cbind( rep(date2[1], length(d_vec)) , rep(date2[3], length(d_vec)),rep(date2[2], length(d_vec)), d_vec))
    
  }else{
    
    date <- sapply(node,function(x){x$getElementText()})
    date_vec <- unlist(date)
    date_vec <- gsub("/","",date_vec)
    date2 <- str_split(date_vec, "\\n")
    date2 <- unlist(date2)
    date2[-2] <- gsub("[^0-9]", "" , date2[-2])
    
    #print(date2)
    
    
    
    node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb30 > table > tbody > tr:nth-child(",j,") > td:nth-child(2)"))
    
    content <- sapply(node, function(x){x$getElementText()})
    con_vec <- unlist(content)
    
    a_vec <- unlist(str_split(con_vec, "\n"))
    b_vec <- str_split(a_vec,": ")
    c_vec <- unlist(b_vec)
    d_vec <- unlist(str_split(c_vec[seq(2,length(c_vec),2)],","))
    #print(d_vec)
    
    mat <- rbind(mat, cbind( rep(date2[1], length(d_vec)) , rep(date2[3], length(d_vec)),rep(date2[2], length(d_vec)), d_vec))
    
  }
  
}



#############################기능사


nodeSubject <- remDr$findElements("css", "#\\30 4")
sapply(nodeSubject, function(x){x$clickElement()}) #클릭

for(j in 1:1000){
  node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > th"))
  
  if(length(node) == 0){
    
    node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > td:nth-child(1)"))
    
    if(length(node) == 0){
      break
    }
    content <- sapply(node, function(x){x$getElementText()})
    con_vec <- unlist(content)
    
    a_vec <- unlist(str_split(con_vec, "\n"))
    b_vec <- str_split(a_vec,": ")
    c_vec <- unlist(b_vec)
    d_vec <- unlist(str_split(c_vec[seq(2,length(c_vec),2)],","))
    #print(d_vec)
    mat <- rbind(mat, cbind( rep(date2[1], length(d_vec)) , rep(date2[2], length(d_vec)),rep(type, length(d_vec)), d_vec))
    
  }else{
    
    date <- sapply(node,function(x){x$getElementText()})
    date_vec <- unlist(date)
    date2 <- str_split(date_vec, "/\\n")
    date2 <- unlist(date2)
    date2 <- gsub("[^0-9]", "" , date2)
    
    if(date2[2] == ""){
      next
    }
    
    
    node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > td.txt_center"))
    type <- sapply(node,function(x){x$getElementText()})
    type <- unlist(type)
    if(type == "시행종목"){
      type <- "기능사"
    }else{
      type <- "기능사2"
    }
    
    #print(date2)
    #print(type)
    
    
    
    node <- remDr$findElements("css", paste0("#viewExamSchd > div.tbl_type4.mb0 > table > tbody > tr:nth-child(",j,") > td:nth-child(3)"))
    
    content <- sapply(node, function(x){x$getElementText()})
    con_vec <- unlist(content)
    
    a_vec <- unlist(str_split(con_vec, "\n"))
    b_vec <- str_split(a_vec,": ")
    c_vec <- unlist(b_vec)
    d_vec <- unlist(str_split(c_vec[seq(2,length(c_vec),2)],","))
    #print(d_vec)
    
    mat <- rbind(mat, cbind( rep(date2[1], length(d_vec)) , rep(date2[2], length(d_vec)),rep(type, length(d_vec)), d_vec))
    
  }
  
}

df <- data.frame(mat,stringsAsFactors = F)
df

write.csv(df, file="kkj.csv", row.names=F, fileEncoding="CP949", quote=F)






