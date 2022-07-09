#큐넷 홈페이지에서 자격증명/수수료/ncs 추출 (한국산업인력공단 주관하는 시험만)

#처음 페이지로 들어가면 ncs코드 대분류별로 나눠져있다(사업관리=01, 경영회계사무=02, ...)
#해당 대분류 클릭하면 중분류별 순서로 나옴
#단, 대분류 경영회계사무(02)처럼 경영/경영(사회조사분석)/경영(소비자전문상담)/경영(컨벤션기획)으로 나눠진 경우
#중분류는 한개로 통합된다(경영=01, 회계=02, ...)

#사이트 분석결과 a태그에 스크립트 함수에서 parameter으로 ncs코드가 들어감(대분류2자리+중분류1자리)
#해당 문자 추출해서 ncs코드 저장(대분류2자리 + '0' + 중분류1자리로 4글자)

#그후에 중분류 돌아가면서 클릭해서 해당 과목이름(cname추출)

#cname 클릭해서 넘어간 페이지에서 수수료 부분 추출하기 

rm(list=ls())


library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
url <- "https://www.q-net.or.kr/crf005.do?id=crf00501&gSite=Q&gId="
remDr$navigate(url)

susu <- matrix(nrow=0,ncol=4)
colnames(susu) = c('cname', 'price', 'ncs', 'company')

#for문 대신에 repeat문 사용하느라고 초기값 지정
i<-1
j<-1
k<-1
f<-F
repeat{
  
  #사이트 구조 분석해보면 x,y,z가 다음과 같이 변화하는것을 볼수있음 
  # x     y     z
  # 1111  1122  1212
  # 2222  1122  1212
  # 3333  1122  1212 ... 이걸 계산해주는 아래 3줄
  x<- ((i-1) %/% 4) +1
  y<- ifelse(((i-1) %% 4) <2 , 1,2)
  z<- ifelse(((i-1) %% 2) ==0 , 1, 2)
  node1 <- remDr$findElements("css" , paste0("#content > div.content > div.tab_lc_wrap_img > div:nth-child(",x,") > ul:nth-child(",y,") > li:nth-child(",z,") > a > span"))
  aa <- sapply(node1, function(x){x$getElementText()})
  if(length(aa)==0) break #값이 없으면 break
  sapply(node1, function(x){x$clickElement()}) #대분류 클릭
  Sys.sleep(1)
  
  
  repeat{
    node2 <- remDr$findElements("css", paste0("#div1_",(i-1)," > ul > li:nth-child(",j,") > a"))
    ncs <- sapply(node2, function(x){x$getElementAttribute("href")})
    ncs <- unlist(ncs)
    ncs <- gsub("[^0-9]{3}", "" , ncs) #ncs코드 3자리 숫자만 추출
    ncs <- paste0(substring(ncs,3,4),"0",substring(ncs,5,5)) #ncs조합해서 4자리로 변경
    bb <- sapply(node2, function(x){x$getElementText()}) 
    if(length(bb)==0) { #중분류 없으면 break
      i <- i+1
      j <- 1
      break
    }
    sapply(node2, function(x){x$clickElement()}) #중분류명 클릭
    Sys.sleep(1)
    
    repeat{
      node3 <- remDr$findElements("css", paste0("#searchJMlist_view > li:nth-child(",k,") > a"))
      cc <- sapply(node3, function(x){x$getElementText()})
      if(length(cc)==0) { #목록 없으면 break
        j <- j+1
        k <- 1
        node5 <- remDr$findElements("css", "span.ui-button-icon-primary.ui-icon.ui-icon-closethick")
        sapply(node5 , function(x){x$clickElement()}) #그냥 break이 아니고 X버튼 눌러서 탈출
        Sys.sleep(2)
        break
      }
      cname <- sapply(node3, function(x){x$getElementText()}) # 자격증명 (cname) 추출
      cname <- unlist(cname)
      sapply(node3, function(x){x$clickElement()}) # 자격증명 클릭해서 페이지 이동
      Sys.sleep(1.5)
      
      node4 <- remDr$findElements("css", "#contentView > div.dlInfo.mb40 > dl > dd:nth-child(2)")
      dd <- sapply(node4, function(x){x$getElementText()}) #수수료 추출
      dd<-unlist(dd)
      cat(cname, dd, ncs,'\n'); 
      susu <- rbind(susu, c(cname, dd, ncs, '한국산업인력공단'))
      f<- T #repeat문 2개 break하려는 flag값
      remDr$navigate(url)
      k<-k+1
      break
    }
    if(f) break
  }
}
df <- data.frame(susu, row.names = NULL, stringsAsFactors = F) 

write.table(df, file="susu.csv", row.names=F,quote=F, col.names = F, sep=";", fileEncoding="CP949")







