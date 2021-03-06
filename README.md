# 專案目的
* 專案目標 : 使用系統將重複的工作快速且有效率達成，並且藉由系統建立資料產生所需報表。

# 專案模組
    Purchasing
    ├── custmer 					                       
	├── prod 						                        
	├── stock 							                        
	├── fixedcost 						                   
    ├── collection 						
    ├── offer							
    ├── checkout						
    ├── order							
    └── report						                      

# 專案架構
    Purchasing
    ├── frontend                        #後台UI程式
    ├── sql                             #資料庫SQL
    └── purchasing                      #後台程式主目錄
        ├── backend                     #後台程式
        ├── backend-common              #後台程式共用元件
        └── shoppe                      #電商訂單處理程式

# backend、shoppe 開發工具
* JDK：8
	* <https://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html>
* Maven 3.6.xx
	* <https://maven.apache.org/index.html>
* MYSQL 5.7.xx
	* <https://dev.mysql.com/downloads/mysql/5.7.html#downloads> 

# frontend 開發工具
* Node.js v10.xx.xx
	* <https://nodejs.org/en/download/> 
	
## backend-common
### 使用指令的方式執行，需要開啟console視窗來執行
* 編譯程式<br>
<code>mvn install</code>
<br>編譯後的結果會存放在mvn的.m2目錄中，讓backend引用

## backend、shoppe
### 以下全部使用指令的方式執行，需要開啟console視窗來執行
* 編譯程式<br>
<code>mvn clean compile</code>
* 編譯及包裝程式<br>
<code>mvn clean package</code>
<br>編譯後的結果會存放在target目錄中

## frontend
* 安裝程式所需要的元件<br>
<code>npm install jquery --save</code><br>
<code>npm install bootstrap --save</code><br>
<code>npm install popper.js --save</code><br>
<code>npm install script-ext-html-webpack-plugin --save</code>

### 連線資訊
* SQL-DW-PROD 127.0.0.1
* MS_SQL: 127.0.0.1 `帳密(root/123456)` DB:`eladmin`
				
