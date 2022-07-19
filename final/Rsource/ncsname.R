
rm(list=ls())


library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
url <- "https://www.q-net.or.kr/crf005.do?id=crf00501&gSite=Q&gId="
remDr$navigate(url)

mat <- matrix(nrow=0,ncol=3)
colnames(mat) = c('code', 'l', 'm')

i<-1
repeat{
  
  x<- ((i-1) %/% 4) +1
  y<- ifelse(((i-1) %% 4) <2 , 1,2)
  z<- ifelse(((i-1) %% 2) ==0 , 1, 2)
  node1 <- remDr$findElements("css" , paste0("#content > div.content > div.tab_lc_wrap_img > div:nth-child(",x,") > ul:nth-child(",y,") > li:nth-child(",z,") > a > span"))
  aa <- sapply(node1, function(x){x$getElementText()})
  if(length(aa)==0) break
  lcode <- unlist(aa)
  sapply(node1, function(x){x$clickElement()})
  Sys.sleep(0.3)
  
  j<-1
  k<-1
  repeat{
    node2 <- remDr$findElements("css" , paste0("#div1_",(i-1)," > ul > li:nth-child(",j,") > a"))
    bb <- sapply(node2, function(x){x$getElementText()})
    if(length(bb)==0) break
    mcode <- unlist(bb)
    if(length(grep("\\(",mcode))==1 ){
      j<-j+1
      next
    }
    mcode <- gsub("· ","", mcode)
    Sys.sleep(0.3)
    
    j<-j+1
    
    mat <- rbind(mat, c(i*100+k, lcode, mcode))
    k<-k+1
    Sys.sleep(0.3)
  }
  i <- i+1
}

df <- data.frame(mat, row.names=NULL, stringsAsFactors = F)


write.table(df, file="ncs.csv", row.names=F,quote=F, col.names = F, sep=";", fileEncoding="CP949")





