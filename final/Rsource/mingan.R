#공인민간자격 데이터 추출해오기
#1.처음 주소 입력하고 들어가는 화면에 보이는 것들 
#-> table 안에 한줄씩 번갈아서 시행기관(company)과 해당 기관에서 시행하는 자격증 목록
#2.해당 자격증 클릭하면 최상단에 자격명(cname)
#3.아래줄에 자격정보=개요(cinfo)
#4.아래에 있는 테이블에 급수(clevel) "(공인)" 붙은 것만 추출, 직무내용(cjob) 추출

rm(list=ls())

library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
url <- "https://www.q-net.or.kr/crf008.do?id=crf00801&gSite=Q&gId="
remDr$navigate(url)



mat <- matrix(nrow=0, ncol=5)
colnames(mat) <- c("cname","company" , "clevel", "cinfo" , "cjob")
count <- 1

for (i in 1:1000){
  
  #자격증 발급기관(company) 추출
  nodecc <- remDr$findElements("css", paste0("#contView > ul > li:nth-child(",i,") > strong"))
  company <- sapply(nodecc, function(x){x$getElementText()})
  if(length(company)==0) break
  company <- unlist(company)
  
  
  
  
  for (j in 1:1000){
    
#    #자격증 이름(cname) 추출
    nodeSubject <- remDr$findElements("css", paste0("#contView > ul > li:nth-child(",i,") > div > ul > li:nth-child(",j,") > a"))
    cname <- sapply(nodeSubject, function(x){x$getElementText()}) 
    if(length(cname)== 0) break
    
    #각 자격증 페이지로 이동(a태그 href attribute를 뽑아내서 페이지를 이동한다)
    #(해당 a태그를 클릭하면 새창이 떠서 remDr이 다른 새 크롬창이 되어버리기 때문.)
    #sapply(nodeSubject, function(x){x$clickElement()}) #클릭
    node <- sapply(nodeSubject, function(x){x$getElementAttribute("href")})
    remDr$navigate(node[[1]])
    Sys.sleep(0.5)
    
    #자격증 이름(cname) 추출
    node <- remDr$findElements("css" , "#contents > section > article > article.content_board > table > tbody > tr:nth-child(1) > td:nth-child(2)")
    cname <- sapply(node, function(x){x$getElementText()})
    cname <- unlist(cname)
    cname <- gsub("\n","",cname)
    
    #자격증 개요(cinfo) 추출
    node <- remDr$findElements("css", "#contents > section > article > article:nth-child(5) > div.text_square.blue_square")
    cinfo <- sapply(node, function(x){x$getElementText()})
    cinfo <- unlist(cinfo)
    cinfo <- gsub("\n","",cinfo)
    
    #각 급수별 직무내용(cjob) 추출
    for( k in 5:1000){
      #k <- 5
      url2 <- paste0("#contents > section > article > article:nth-child(5) > table:nth-child(",k,") > ")
      ss <- remDr$findElements("css", paste0(url2,"thead > tr > th"))
      jobtitle <- sapply(ss , function(x){x$getElementText()})
      jobtitle <- unlist(jobtitle)
      jobtitle <- gsub("\n", "", jobtitle)
      
      if(length(jobtitle)==0) break #내용 없으면 break
      if(length(grep("공인", jobtitle))!=0){ #공인 붙어있는 급수만 추출 
        sd <- remDr$findElements("css" ,paste0(url2, "tbody > tr > td"))
        cjob <- sapply(sd , function(x){x$getElementText()})
        cjob <- unlist(cjob)
        cjob <- gsub("\n","",cjob)
        
        #print(paste(cname, cinfo, jobtitle, job))
        mat <- rbind(mat , c(cname, company, jobtitle, cinfo, cjob))
        
      }
    }
    
    
    remDr$navigate(url) #다시 처음 url로 돌아온다
    Sys.sleep(0.5)
    
    print(count)
    count <- count + 1
  }
  
}

df <- data.frame(mat, row.names = NULL, stringsAsFactors = F)
str(head(df))

df$clevel <- gsub(" \\(공인\\)", "", df$clevel) #뽑아온 clevel에서 "(공인)" 텍스트 제거

write.csv(df, file="mingan.csv", quote=F, fileEncoding="CP949",row.names=F)
write.table(df, file="mingan.csv", row.names=F,quote=F, col.names = F, sep=";", fileEncoding="CP949")




