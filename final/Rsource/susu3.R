#국가기술자격 연도별 필기/실기 응시/합격자수 추출

#susu.R과 코드는 거의 동일하고
#가장 내부 for문에 수수료 가져오는 것만 기본정보 탭을 하나더 눌러서 테이블을 가져오는것만 다르다
#2000년보다 과거데이터는 "19xx~2000" 형식으로 한번에 묶여있어서 가져오지 않음(2001년 이후 데이터만 추출)


rm(list=ls())


library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
url <- "https://www.q-net.or.kr/crf005.do?id=crf00501&gSite=Q&gId="
remDr$navigate(url)

susu3 <- matrix(nrow=0,ncol=6)
colnames(susu3) = c('cname', 'cyear', 'docApp', 'docPass','pracApp', 'pracPass')

i<-1
j<-1
k<-1
f<-F
repeat{
  
  x<- ((i-1) %/% 4) +1
  y<- ifelse(((i-1) %% 4) <2 , 1,2)
  z<- ifelse(((i-1) %% 2) ==0 , 1, 2)
  node1 <- remDr$findElements("css" , paste0("#content > div.content > div.tab_lc_wrap_img > div:nth-child(",x,") > ul:nth-child(",y,") > li:nth-child(",z,") > a > span"))
  aa <- sapply(node1, function(x){x$getElementText()})
  if(length(aa)==0) break
  sapply(node1, function(x){x$clickElement()})
  Sys.sleep(0.5)
  
  
  repeat{
    node2 <- remDr$findElements("css", paste0("#div1_",(i-1)," > ul > li:nth-child(",j,") > a"))
    bb <- sapply(node2, function(x){x$getElementText()})
    if(length(bb)==0) {
      i <- i+1
      j <- 1
      break
    }
    sapply(node2, function(x){x$clickElement()})
    Sys.sleep(0.5)
    
    repeat{
      node3 <- remDr$findElements("css", paste0("#searchJMlist_view > li:nth-child(",k,") > a"))
      cc <- sapply(node3, function(x){x$getElementText()})
      if(length(cc)==0) {
        j <- j+1
        k <- 1
        node5 <- remDr$findElements("css", "span.ui-button-icon-primary.ui-icon.ui-icon-closethick")
        sapply(node5 , function(x){x$clickElement()})
        Sys.sleep(1)
        break
      }
      cname <- sapply(node3, function(x){x$getElementText()})
      cname <- unlist(cname)
      sapply(node3, function(x){x$clickElement()})
      Sys.sleep(1)
      
      node4 <- remDr$findElements("css", "#tab2")
      sapply(node4, function(x){x$clickElement()})
      Sys.sleep(1)
      
      l <- 1
      repeat{
        node6 <- remDr$findElements("css", paste0("#contentView > div.tbl_normal.tdCenter > table > tbody > tr:nth-child(",l,") > td:nth-child(2)"))
        cyear <- sapply(node6, function(x){x$getElementText()})
        if(length(cyear)==0) break
        cyear <- unlist(cyear)
        if(length(grep("~",cyear))==1) break
        
        node6 <- remDr$findElements("css", paste0("#contentView > div.tbl_normal.tdCenter > table > tbody > tr:nth-child(",l,") > td:nth-child(3)"))
        docApp <- sapply(node6, function(x){x$getElementText()})
        docApp <- unlist(docApp)
        docApp <- gsub(",", "", docApp)
        node6 <- remDr$findElements("css", paste0("#contentView > div.tbl_normal.tdCenter > table > tbody > tr:nth-child(",l,") > td:nth-child(4)"))
        docPass <- sapply(node6, function(x){x$getElementText()})
        docPass <- unlist(docPass)
        docPass <- gsub(",", "", docPass)
        node6 <- remDr$findElements("css", paste0("#contentView > div.tbl_normal.tdCenter > table > tbody > tr:nth-child(",l,") > td:nth-child(6)"))
        pracApp <- sapply(node6, function(x){x$getElementText()})
        pracApp <- unlist(pracApp)
        pracApp <- gsub(",", "", pracApp)
        node6 <- remDr$findElements("css", paste0("#contentView > div.tbl_normal.tdCenter > table > tbody > tr:nth-child(",l,") > td:nth-child(7)"))
        pracPass <- sapply(node6, function(x){x$getElementText()})
        pracPass <- unlist(pracPass)
        pracPass <- gsub(",", "", pracPass)
        
        susu3 <- rbind(susu3, c(cname, cyear, docApp, docPass, pracApp, pracPass))
        cat(cname, cyear, docApp, docPass, pracApp, pracPass,'\n')
        
        l <- l+1;
      }
      
      f<- T
      remDr$navigate(url)
      k<-k+1
      break
    }
    if(f) break
  }
}

df3<- data.frame(susu3 , row.names = NULL, stringsAsFactors = F)
write.table(df3, "susu3.csv", quote=F, sep=";", col.names=F, fileEncoding="CP949", row.names=F)
