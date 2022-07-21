#kki 크롤링해온 페이지에서 상단의 일정표를 가져옵니다
#연도/회차는 kki 가져온 페이지와 동일하게 "/+엔터"로 split후 숫자만 추출했고
#일정 날짜의 경우 . - 그외 기타 문자열 다 삭제후 숫자만 추출해서 8자리씩 끊었습니다
#(YYYYMMDD형식으로 java문자열로 저장할 계획)
#8자리 = 하루만에 끝나는 일정 / 16자리 YYYYMMDD~YYYYMMDD / 32자리 YYYYMMDD~YYYYMMDD + 추가접수

rm(list=ls())

library(httr)
library(RSelenium)
library(stringr)


remDr <- remoteDriver(remoteServerAddr="localhost",port=4445,browserName="chrome")
remDr$open()
remDr$navigate("https://www.q-net.or.kr/crf021.do?id=crf02101&gSite=Q&gId=&scheType=01")



mat <- matrix(nrow=0, ncol=10)
colnames(mat) <- c("연도", "회차", "분류", "필기접수", "필기시험", "필기발표",
                   "서류제출", "실기접수", "실기시험", "실기발표")



for (k in 1:4){

  nodeSubject <- remDr$findElements("css", paste0("#\\30 ",k))
  sapply(nodeSubject, function(x){x$clickElement()}) #클릭
  Sys.sleep(1)

  if( k ==4 ){
    l=7
  }else{
    l=8
  }
  for(i in 1:1000){
    node <- remDr$findElements("css", paste0("#viewExamSchd > ul > li table > tbody > tr:nth-child(",i,") > td:nth-child(1)"))
    if(length(node)==0){
      print("---------------------------------------------------")
      break
    }
    v <- vector(mode="character", length=10)
    for(j in 1:l){
      node <- remDr$findElements("css", paste0("#viewExamSchd > ul > li table > tbody > tr:nth-child(",i,") > td:nth-child(",j,")"))
      date <- sapply(node,function(x){x$getElementText()})
      
      if(j == 1){
        date <- gsub("\\n","",date)
        
        #clevel 추출하는 부분
        if(k==1){
          v[3] = "기술사"
        }else if(k==2){
          v[3] = "기능장"
        }else if(k==4){
          v[3] = "기능사"
        }else if( length(grep("산업기사", date)) != 0 ){
          v[3] = "산업기사"
        }else {
          v[3] = "기사"
        }
        
        date <- str_split(date,"/")
        date <- unlist(date)
        date <- gsub("[^0-9]", "", date)
        v[1] = date[1] #cyear
        v[2] = date[2] #cround
        if(date[2]==""){ #2022년/없음 -> 기능사 특성화고 전형 -> break
          break
        }
      }else{
        date <- gsub("[^0-9]", "", date) #숫자 제외하고 다 삭제 = 숫자만 추출
        if( k==4 & j>=5){
          v[j+3] = date
        }else{
          v[j+2] = date
        }
      }
    }
    if(j>5){
      mat <- rbind(mat,v)
    }
  }
}

df <- data.frame(mat, row.names = NULL)
df

#숫자가 16자리 넘어가면 엑셀에서 뒷부분이 짤림
#="숫자"로 감싸서 문자열로 인식하게 함 (자바에서는 다시 이부분을 지워야 한다)
df$필기접수 <- paste0('"=""', df$필기접수, '"""')
df$필기시험 <- paste0('"=""', df$필기시험, '"""')
df$서류제출 <- paste0('"=""', df$서류제출, '"""')
df$실기접수 <- paste0('"=""', df$실기접수, '"""')
df$실기시험 <- paste0('"=""', df$실기시험, '"""')
df$실기발표 <- paste0('"=""', df$실기발표, '"""')
write.table(df, file="qnetdate.csv", row.names=F, fileEncoding="CP949", quote=F, col.names = F, sep=",")










