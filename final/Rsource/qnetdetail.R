#qnetfinal.csv

rm(list=ls())


library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
url <- "https://www.q-net.or.kr/crf005.do?id=crf00501&gSite=Q&gId="
remDr$navigate(url)

mat <- matrix(nrow=0,ncol=5)
colnames(mat) = c('cname', 'cinfo', 'cjob', 'homepage', 'cmethod')

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
  Sys.sleep(1)
  
  
  repeat{
    node2 <- remDr$findElements("css", paste0("#div1_",(i-1)," > ul > li:nth-child(",j,") > a"))
    bb <- sapply(node2, function(x){x$getElementText()})
    if(length(bb)==0) {
      i <- i+1
      j <- 1
      break
    }
    sapply(node2, function(x){x$clickElement()})
    Sys.sleep(1)
    
    repeat{
      node3 <- remDr$findElements("css", paste0("#searchJMlist_view > li:nth-child(",k,") > a"))
      cc <- sapply(node3, function(x){x$getElementText()})
      if(length(cc)==0) {
        j <- j+1
        k <- 1
        node5 <- remDr$findElements("css", "span.ui-button-icon-primary.ui-icon.ui-icon-closethick")
        sapply(node5 , function(x){x$clickElement()})
        Sys.sleep(2)
        break
      }
      cname <- sapply(node3, function(x){x$getElementText()})
      cname <- unlist(cname)
      sapply(node3, function(x){x$clickElement()})
      Sys.sleep(1.5)
      
      l<-1
      cinfo <- ""
      cjob <- ""
      homepage <- ""
      cmethod <- ""
      repeat{
        node4 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dt:nth-child(",2*l-1,")"))
        dd <- sapply(node4, function(x){x$getElementText()})
        if(length(dd)==0) break
        dd <- unlist(dd)
        if(dd == "개요"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cinfo <- ff
            remDr$switchToFrame(NULL)
          }else{
            cinfo <- ee
          }
          cinfo <- gsub("\n","", cinfo)
        }
        if(dd == "수행직무"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cjob <- ff
            remDr$switchToFrame(NULL)
          }else{
            cjob <- ee
          }
          cjob <- gsub("\n","", cjob)
        }
        if(dd == "진로 및 전망"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cjob <- ff
            remDr$switchToFrame(NULL)
          }else{
            cjob <- ee
          }
          cjob <- gsub("\n","", cjob)
        }
        if(dd == "실시기관 홈페이지"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            homepage <- ff
            remDr$switchToFrame(NULL)
          }else{
            homepage <- ee
          }
          homepage <- gsub("\n","", homepage)
        }
        if(dd == "취득방법"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cmethod <- ff
            remDr$switchToFrame(NULL)
          }else{
            cmethod <- ee
          }
          cmethod <- gsub("\n","", cmethod)
        }
          
        l <- l+1
      }
      
      
      node4 <- remDr$findElements("css", "#tab2")
      sapply(node4, function(x){x$clickElement()})
      Sys.sleep(1.5)
      
      l<-1
      repeat{
        node4 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dt:nth-child(",2*l-1,")"))
        dd <- sapply(node4, function(x){x$getElementText()})
        if(length(dd)==0) break
        dd <- unlist(dd)
        if(dd == "개요"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cinfo <- ff
            remDr$switchToFrame(NULL)
          }else{
            cinfo <- ee
          }
          cinfo <- gsub("\n","", cinfo)
        }
        if(dd == "수행직무"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cjob <- ff
            remDr$switchToFrame(NULL)
          }else{
            cjob <- ee
          }
          cjob <- gsub("\n","", cjob)
        }
        if(dd == "진로 및 전망"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cjob <- ff
            remDr$switchToFrame(NULL)
          }else{
            cjob <- ee
          }
          cjob <- gsub("\n","", cjob)
        }
        if(dd == "실시기관 홈페이지"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            homepage <- ff
            remDr$switchToFrame(NULL)
          }else{
            homepage <- ee
          }
          homepage <- gsub("\n","", homepage)
        }
        if(dd == "취득방법"){
          node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,")"))
          ee <- sapply(node6, function(x){x$getElementText()})
          ee <- unlist(ee)
          if(ee == ""){
            node6 <- remDr$findElements("css", paste0("#contentView > div.dlInfo.mb40 > dl > dd:nth-child(",2*l,") > iframe"))
            remDr$switchToFrame(node6[[1]])
            node7 <- remDr$findElements("css","body")
            ff <- sapply(node7, function(x){x$getElementText()})
            ff <- unlist(ff)
            cmethod <- ff
            remDr$switchToFrame(NULL)
          }else{
            cmethod <- ee
          }
          cmethod <- gsub("\n","", cmethod)
        }
        
        l <- l+1
      }
      
      print(cname)
      mat <- rbind(mat , c(cname, cinfo, cjob, homepage, cmethod))
      
      
      
      
      f<- T
      remDr$navigate(url)
      k<-k+1
      break
    }
    if(f) break
  }
}

df <- data.frame(mat, row.names = NULL, stringsAsFactors = F) 


df

write.table(df, file="dddd.csv", row.names=F,quote=F, col.names = F, sep=";", fileEncoding="CP949")

