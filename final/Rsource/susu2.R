#한국산업인력공단 이외 기관에서 시행하는 국가기술자격 목록

#susu.R과 기본적으로 동일한 코드
#차이점은 시행기관별로 for문을 하나 더 돌아야 한다 (company 추출)
#그외 반복문 돌아가는 로직은 같고 차이점은 css구조정도와
#수수료정보가 제공되지 않으므로 세부정보 페이지까지 들어가지는 않아도 되는것

rm(list=ls())


library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
url <- "https://www.q-net.or.kr/crf005.do?id=crf00501&gSite=Q&gId="
remDr$navigate(url)

susu2 <- matrix(nrow=0,ncol=3)
colnames(susu2) = c('cname', 'ncs', 'company')

i<-1
repeat{
  
  x<- ((i-1) %/% 4) +1
  y<- ifelse(((i-1) %% 4) <2 , 1,2)
  z<- ifelse(((i-1) %% 2) ==0 , 1, 2)
  node1 <- remDr$findElements("css" , paste0("#content > div.content > div.tab_lc_wrap.othter > div:nth-child(",x,") > ul:nth-child(",y,") > li:nth-child(",z,") > a > span"))
  aa <- sapply(node1, function(x){x$getElementText()})
  company <- unlist(aa)
  if(length(aa)==0) break
  sapply(node1, function(x){x$clickElement()})
  Sys.sleep(0.5)
  
  j<-1
  repeat{
    node2 <- remDr$findElements("css" , paste0("#div2_",(i-1)," > ul > li:nth-child(",j,") > a"))
    bb <- sapply(node2, function(x){x$getElementText()})
    if(length(bb)==0) break
    sapply(node2,function(x){x$clickElement()})
    Sys.sleep(0.5)
    
    k<-1
    repeat{
      node3 <- remDr$findElements("css", paste0("#searchJMlist_view > li:nth-child(",k,") > a"))
      cc <- sapply(node3, function(x){x$getElementText()})
      if(length(cc)==0) break
      ncs <- sapply(node3, function(x){x$getElementAttribute("onclick")})
      ncs <- unlist(ncs)
      ncs <- gsub("[^0-9]{3}","",ncs)
      ncs <- paste0(substring(ncs,1,2),"0",substring(ncs,3,3))
      
      sapply(node3, function(x){x$clickElement()})
      Sys.sleep(0.5)
      
      l<-1
      repeat{
        node4 <- remDr$findElements("css", paste0("#list2 > div > ul > li:nth-child(",l,") > a"))
        dd <- sapply(node4, function(x){x$getElementText()})
        if(length(dd)==0) break
        cname <- unlist(dd)
        susu2 <- rbind(susu2, c(cname, ncs,company))
        
        l<-l+1
      }
      k<-k+1
    }
    j<-j+1
    node5 <- remDr$findElements("css", "span.ui-button-icon-primary.ui-icon.ui-icon-closethick")
    sapply(node5 , function(x){x$clickElement()})
    Sys.sleep(0.7)
  }
  i <- i+1
}

df2 <- data.frame(susu2, row.names=NULL, stringsAsFactors = F)


write.table(df2, file="susu2.csv", row.names=F,quote=F, col.names = F, sep=";", fileEncoding="CP949")





