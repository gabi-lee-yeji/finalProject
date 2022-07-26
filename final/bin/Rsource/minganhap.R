#mingan.R 에서와 동일한 페이지에서 자격증/연도/급수별 접수자/응시자/취득자수 추출
#마찬가지로 급수(clevel)에 "(공인)" 문자열 있는것만 추출한다 
#나머지 코드는 mingan.R과 동일 

rm(list=ls())

library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
url <- "https://www.q-net.or.kr/crf008.do?id=crf00801&gSite=Q&gId="
remDr$navigate(url)



mat <- matrix(nrow=0, ncol=6)
colnames(mat) <- c("cname","clevel" , "cyear", "applied" , "tested", "passed")
count <- 1


for (i in 1:1000){
  
  #자격증 발급기관(company) 추출
  nodecc <- remDr$findElements("css", paste0("#contView > ul > li:nth-child(",i,") > strong"))
  company <- sapply(nodecc, function(x){x$getElementText()})
  if(length(company)==0) break
  company <- unlist(company)
  
  
  for (j in 1:1000){
    #i<-1
    #j<-1
    
    nodeSubject <- remDr$findElements("css", paste0("#contView > ul > li:nth-child(",i,") > div > ul > li:nth-child(",j,") > a"))
    cname <- sapply(nodeSubject, function(x){x$getElementText()}) 
    if(length(cname)== 0) break
    
    #각 자격증 페이지로 이동(a태그 href attribute)
    #sapply(nodeSubject, function(x){x$clickElement()}) #클릭
    node <- sapply(nodeSubject, function(x){x$getElementAttribute("href")})
    remDr$navigate(node[[1]])
    Sys.sleep(0.5)
    
    
    #자격증 이름(cname) 추출
    node <- remDr$findElements("css" , "#contents > section > article > article.content_board > table > tbody > tr:nth-child(1) > td:nth-child(2)")
    cname <- sapply(node, function(x){x$getElementText()})
    cname <- unlist(cname)
    cname <- gsub("\n"," ",cname)
    #print(cname)
    
    
    for( k in 1:1000){
      pnode <- remDr$findElements("css", paste0("#grdStatTb > tbody > tr:nth-child(",k,") > td:nth-child(2)"))
      clevel <- sapply(pnode, function(x){x$getElementText()})
      clevel <- unlist(clevel)
      if(length(clevel)==0) break
      if(length(grep("(공인)", clevel))!=0){
        pnode2 <- remDr$findElements("css", paste0("#grdStatTb > tbody > tr:nth-child(",k,") > td:nth-child(1)"))
        cyear <- sapply(pnode2, function(x){x$getElementText()})
        cyear <- unlist(cyear)
        
        pnode2 <- remDr$findElements("css", paste0("#grdStatTb > tbody > tr:nth-child(",k,") > td:nth-child(4)"))
        applied <- sapply(pnode2, function(x){x$getElementText()})
        applied <- unlist(applied)
        
        pnode2 <- remDr$findElements("css", paste0("#grdStatTb > tbody > tr:nth-child(",k,") > td:nth-child(5)"))
        tested <- sapply(pnode2, function(x){x$getElementText()})
        tested <- unlist(tested)
        
        pnode2 <- remDr$findElements("css", paste0("#grdStatTb > tbody > tr:nth-child(",k,") > td:nth-child(6)"))
        passed <- sapply(pnode2, function(x){x$getElementText()})
        passed <- unlist(passed)
        
        cat(cname, clevel, cyear, applied, tested, passed, "\n")
        mat <- rbind(mat, c(cname, clevel, cyear, applied, tested, passed))
      }else{
        pnode <- remDr$findElements("css", paste0("#grdStatTb > tbody > tr:nth-child(",k,") > td:nth-child(1)"))
        clevel <- sapply(pnode, function(x){x$getElementText()})
        clevel <- unlist(clevel)
        if(length(grep("(공인)", clevel))!=0){
          
          pnode2 <- remDr$findElements("css", paste0("#grdStatTb > tbody > tr:nth-child(",k,") > td:nth-child(2)"))
          passed <- sapply(pnode2, function(x){x$getElementText()})
          passed <- unlist(passed)
          
          cat(cname, clevel, cyear, applied, tested, passed, "\n")
          mat <- rbind(mat, c(cname, clevel, cyear, applied, tested, passed))
        }
      }
    }
    
    
    remDr$navigate(url)
    Sys.sleep(0.5)
    
    print(count)
    count <- count + 1
  }
}


df <- data.frame(mat, row.names = NULL, stringsAsFactors = F)
str(head(df))

df$clevel <- gsub("\\(공인\\)", "", df$clevel)

write.table(df, file="minganstat2.csv", row.names=F,quote=F, col.names = F, sep=";", fileEncoding="CP949")




