package com.example.demo.service;


import java.time.LocalDate;

import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.JobPosting;
import com.example.demo.model.JobPosting.EmploymentType;
import com.example.demo.model.JobPosting.WorkingHourType;


@Service
public class RandomValueService {
		
	
    public String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex)); 
        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String password = encoder.encode(sb.toString());
        return sb.toString();
    }
    public String RandomNameGenerator() {
    	List<String> list = new ArrayList<String>();
    	Collections.addAll(list,"郡川",
    	                         "宇賀塚",
    	                         "折谷",
    	                         "髙久",
    	                         "藥王",
    	                         "佐藤󠄁",
    	                         "佐方",
    	                         "鍜野",
    	                         "隅川",
    	                         "関内",
    	                         "杉森",
    	                         "菖蒲",
    	                         "真楽",
    	                         "土橋",
    	                         "相澤",
    	                         "何田",
    	                         "佐藤",
    	                         "鈴木",
    	                         "高橋",
    	                         "田中",
    	                         "伊藤",
    	                         "渡辺",
    	                         "山本",
    	                         "中村",
    	                         "小林",
    	                         "加藤",
    	                         "吉田",
    	                         "山田",
    	                         "佐々木",
    	                         "山口",
    	                         "松本",
    	                         "井上",
    	                         "木村",
    	                         "林",
    	                         "斎藤",
    	                         "清水",
    	                         "山崎",
    	                         "森",
    	                         "池田",
    	                         "橋本",
    	                         "阿部",
    	                         "石川",
    	                         "山下",
    	                         "中島",
    	                         "石井",
    	                         "小川",
    	                         "前田",
    	                         "岡田",
    	                         "長谷川",
    	                         "藤田",
    	                         "後藤",
    	                         "近藤",
    	                         "村上",
    	                         "遠藤",
    	                         "青木",
    	                         "坂本",
    	                         "斉藤",
    	                         "福田"
    	                     );
    	// 乱数生成器
        Random random = new Random();
        
        //ランダムな数を生成 (0からdayPeriod-1)
       
       	 Integer nameIndex = random.nextInt(list.size());
        	String name = list.get(nameIndex);
//        	return name + "三郎";
        	return name;
    }
    
    public String RandomEmailGenerator() {
      	// バージョン4のUUIDを生成（ランダムな128ビットの数値）
	      UUID uuid = UUID.randomUUID();
	      
	      // UUID文字列からハイフンを取り除き、ドメイン名と結合する
	      // UUIDは合計32桁の16進数で構成される
	      String emailLocalPart = uuid.toString().replace("-", "");
	      String domain = "example.com"; // 適切なドメインに変更してください
	
	      return emailLocalPart + "@" + domain;
    }
    
    public Date RandomDategenerator() {
    	
	   // 開始日: 2020年1月1日, 終了日: 2025年12月31日
	      LocalDate startDate = LocalDate.of(2020, 1, 1);
	      LocalDate endDate = LocalDate.now(); // 現在の日付
	
	      // EPOCH DAY (1970-01-01からの日数) を取得
	      long startEpochDay = startDate.getLong(ChronoField.EPOCH_DAY);
	      long endEpochDay = endDate.getLong(ChronoField.EPOCH_DAY);
	
	      // 範囲内の日数を計算
	      long dayPeriod = endEpochDay - startEpochDay;
	
	      // 乱数生成器
	      Random random = new Random();
	
	      // ランダムな日数を生成 (0からdayPeriod-1)
	      int randomDays = random.nextInt((int) dayPeriod);
	
	      // 基準日からランダムな日数を加算
	      LocalDate randomDate = startDate.plusDays(randomDays);
	
	      System.out.println("開始日: " + startDate);
	      System.out.println("終了日: " + endDate);
	      System.out.println("ランダムな日付: " + randomDate);
	      Date ParseDate = Date.from(randomDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());


	      return ParseDate;
    }
    
    public String RandomGendergenerator() {
    	 List<String> list = new ArrayList<String>();
    	 Collections.addAll(list, "男性", "女性", "無回答");
    	 
    // 乱数生成器
	      Random random = new Random();
	      
	  //ランダムな性別を生成 (0からdayPeriod-1)
	      int randomIndex = random.nextInt(3);
	      
	      String randomGender = list.get(randomIndex);
	      
	      return randomGender;
    }
    
    public List<String> RandomWorkplaces() {
    	List<String> list = new ArrayList<String>();
    	Collections.addAll(list,"北海道",
    			"青森県",
    			"秋田県",
    			"岩手県",
    			"山形県",
    			"宮城県",
    			"福島県",
    			"茨城県",
    			"栃木県",
    			"群馬県",
    			"埼玉県",
    			"千葉県",
    			"東京都",
    			"神奈川県",
    			"山梨県",
    			"長野県",
    			"新潟県",
    			"富山県",
    			"石川県",
    			"福井県",
    			"静岡県",
    			"愛知県",
    			"岐阜県",
    			"三重県",
    			"滋賀県",
    			"京都府",
    			"大阪府",
    			"奈良県",
    			"和歌山県",
    			"兵庫県",
    			"鳥取県",
    			"島根県",
    			"岡山県",
    			"広島県",
    			"山口県",
    			"香川県",
    			"愛媛県",
    			"徳島県",
    			"高知県",
    			"福岡県",
    			"大分県",
    			"熊本県",
    			"佐賀県",
    			"長崎県",
    			"宮崎県",
    			"鹿児島県",
    			"沖縄県");
    	
   // 乱数生成器
      Random random = new Random();
      
      String randomWorkplace = "";
      List<String> workplacelist = new ArrayList<String>();
      //ランダムな数を生成 (0からdayPeriod-1)
      int randomIndex = random.nextInt(3);
      int workIndex = 0;
      for(int i=0;i<=randomIndex;i++) {
      	workIndex = random.nextInt(47);
      	randomWorkplace = list.get(workIndex);
      	workplacelist.add(randomWorkplace);
      }
      return workplacelist;
    }
    public List<String> RandomCategories() {
    List<String> list = new ArrayList<String>();
    Collections.addAll(list,
    "営業・コールセンター",
    "事務・受付",
    "経営・管理・人事",
    "広告・マーケティング",
    "販売・接客・サービス・モニター",
    "飲食・フード",
    "旅行・ホテル・ブライダル",
    "エンタメ・レジャー・スポーツ",
    "工場・製造",
    "警備・清掃・点検",
    "軽作業・倉庫",
    "建築・設計・施工・土木",
    "配達・運転・物流",
    "教育・保育",
    "介護・福祉",
    "医療・看護",
    "IT・Web",
    "技術職（電気,化学,医薬品）",
    "クリエイティブ・デザイン・ゲーム",
    "映像・芸能",
    "美容・理容",
    "アパレル・インテリア",
    "コンサルタント・士業・通訳",
    "金融・不動産",
    "農業・林業・水産業・公務員",
    "ナイトワーク"
    );
   
    // 乱数生成器
       Random random = new Random();
       
       List<String> categorylist = new ArrayList<String>();
       String randomcategory = "";
       
       //ランダムな数を生成 (0からdayPeriod-1)
       int randomIndex = random.nextInt(3);
       int categoryIndex = 0;
       for(int i=0;i<=randomIndex;i++) {
      	 categoryIndex = random.nextInt(26);
       	randomcategory = list.get(categoryIndex);
       	categorylist.add(randomcategory);
       }
       
       return categorylist;
    }
    
    public List<String> RandomJobs(Integer rIndex) {
    
    List<String> list = new ArrayList<String>();
    Collections.addAll(list,
    		
    		    "営業・コールセンター:法人営業",
//    		    "営業・コールセンター:企画営業",
//    		    "営業・コールセンター:カスタマーサポート",
//    		    "営業・コールセンター:テレアポ",
//    		    "営業・コールセンター:キャリアカウンセラー",
    		    
    		    "事務・受付:一般事務",
    		    "事務・受付:受付",
//    		    "事務・受付:秘書",
//    		    "事務・受付:医療事務",
//    		    "事務・受付:データ入力",
//    		    "事務・受付:総務事務",
//    		    "事務・受付:金融事務",
//    		    "経営・管理・人事:経営企画",
//    		    "経営・管理・人事:事業企画",
//    		    "経営・管理・人事:人事",
//    		    "経営・管理・人事:経理",
//    		    "経営・管理・人事:法務",
//    		    "経営・管理・人事:財務",
//    		    "経営・管理・人事:税務",
//    		    "経営・管理・人事:内部監査",
//    		    "経営・管理・人事:内部統制",
    		    
    		    "広告・マーケティング:広報",
    		    "広告・マーケティング:広告プランナー",
//    		    "広告・マーケティング:商品企画",
//    		    "広告・マーケティング:販売促進",
//    		    "広告・マーケティング:コピーライター",
//    		    "広告・マーケティング:データアナリスト",
//    		    "販売・接客・サービス・モニター:コンビニ",
//    		    "販売・接客・サービス・モニター:スーパーマーケット",
//    		    "販売・接客・サービス・モニター:家電量販店",
//    		    "販売・接客・サービス・モニター:ガソリンスタンド",
//    		    "販売・接客・サービス・モニター:ドラッグストア",
//    		    "販売・接客・サービス・モニター:家事代行",
//    		    "飲食・フード:ホールスタッフ",
//    		    "飲食・フード:キッチンスタッフ",
    		    "飲食・フード:カフェ",
//    		    "飲食・フード:居酒屋",
//    		    "飲食・フード:調理師",
//    		    "飲食・フード:パティシエ",
//    		    
//    		    "旅行・ホテル・ブライダル:旅行企画",
//    		    "旅行・ホテル・ブライダル:ホテル",
//    		    "旅行・ホテル・ブライダル:結婚式場",
//    		    "旅行・ホテル・ブライダル:旅館",
//    		    "旅行・ホテル・ブライダル:ウェディングプランナー",
//    		    "旅行・ホテル・ブライダル:旅行カウンター"

    		    "エンタメ・レジャー・スポーツ:イベント",
//    		    "エンタメ・レジャー・スポーツ:カラオケ",
//    		    "エンタメ・レジャー・スポーツ:スポーツ施設",
    		    "エンタメ・レジャー・スポーツ:遊園地",
//    		    "エンタメ・レジャー・スポーツ:温泉",
//    		    "エンタメ・レジャー・スポーツ:ヨガインストラクター",
//    		    
    		    "工場・製造:生産管理",
//    		    "工場・製造:金属加工",
//    		    "工場・製造:溶接工",
//    		    "工場・製造:ライン作業",
//    		    "工場・製造:板金工",
//    		    
//    		    "警備・清掃・点検:車両誘導",
//    		    "警備・清掃・点検:ハウスクリーニング",
//    		    "警備・清掃・点検:ビルメンテナンス",
//    		    "警備・清掃・点検:清掃員",
//    		    "警備・清掃・点検:ごみ収集作業員",
//    		    
    		    "軽作業・倉庫:梱包",
//    		    "軽作業・倉庫:倉庫管理",
//    		    "軽作業・倉庫:シール貼り",
//    		    "軽作業・倉庫:ティッシュ配り",
//    		    "軽作業・倉庫:サンプリング",
//    		    "軽作業・倉庫:試験監督",
//    		    "軽作業・倉庫:在庫管理",
//    		    
//    		    "建築・設計・施工・土木:土木設計",
    		    "建築・設計・施工・土木:建築士",
//    		    "建築・設計・施工・土木:地質調査",
//    		    "建築・設計・施工・土木:大工",
//    		    "建築・設計・施工・土木:現場監督",
//    		    "建築・設計・施工・土木:施工管理",
//    		    "建築・設計・施工・土木:内装設計",
//    		    "建築・設計・施工・土木:耐震診断",
//    		    "配達・運転・物流:配送ドライバー",
//    		    "配達・運転・物流:引越しスタッフ",
//    		    "配達・運転・物流:新聞配達",
//    		    "配達・運転・物流:バス運転手",
//    		    "配達・運転・物流:タクシー",
//    		    "教育・保育:塾講師",
//    		    "教育・保育:保育士",
//    		    "教育・保育:学童保育",
//    		    "教育・保育:ベビーシッター",
//    		    "教育・保育:教習指導員",
//    		    "教育・保育:パソコン講師",
//    		    "教育・保育:英会話講師",
//    		    "介護・福祉:介護士",
//    		    "介護・福祉:ケアマネジャー",
//    		    "介護・福祉:社会福祉士",
//    		    "介護・福祉:生活相談員",
//    		    "介護・福祉:ホームヘルパー",
//    		    "介護・福祉:児童指導員",
//    		    "医療・看護:医師",
//    		    "医療・看護:看護師",
//    		    "医療・看護:薬剤師",
//    		    "医療・看護:整体師",
//    		    "医療・看護:栄養士",
//    		    "医療・看護:鍼灸師",
//    		    "医療・看護:保健師",
//    		    "医療・看護:産業医",
//    		    "医療・看護:歯科衛生士",
//    		    "IT・Web:ITアーキテクト",
//    		    "IT・Web:Webデザイナー",
//    		    "IT・Web:データサイエンティスト",
    		    "IT・Web:サイト運営",
//    		    "技術職（電気:化学:医薬品）:電気設計",
//    		    "技術職（電気:化学:医薬品）:機械エンジニア",
//    		    "技術職（電気:化学:医薬品）:医薬品開発",
//    		    "技術職（電気:化学:医薬品）:回路設計",
//    		    "技術職（電気:化学:医薬品）:化学分析",
//    		    "技術職（電気:化学:医薬品）:半導体エンジニア",
//    		    "クリエイティブ・デザイン・ゲーム:雑誌編集",
//    		    "クリエイティブ・デザイン・ゲーム:イラストレーター",
//    		    "クリエイティブ・デザイン・ゲーム:ライター",
//    		    "クリエイティブ・デザイン・ゲーム:アニメーター",
//    		    "クリエイティブ・デザイン・ゲーム:サウンドクリエイター",
//    		    "映像・芸能:映像制作",
//    		    "映像・芸能:モデル",
//    		    "映像・芸能:芸能マネージャー",
//    		    "映像・芸能:フォトグラファー",
//    		    "映像・芸能:エキストラ",
//    		    "美容・理容:マッサージ",
//    		    "美容・理容:エステティシャン",
//    		    "美容・理容:ネイリスト",
//    		    "美容・理容:セラピスト",
//    		    "美容・理容:着付け",
//    		    "美容・理容:美容部員",
//    		    "美容・理容:脱毛",
//    		    "アパレル・インテリア:スタイリスト",
//    		    "アパレル・インテリア:インテリアコーディネーター",
//    		    "アパレル・インテリア:アパレルバイヤー",
//    		    "アパレル・インテリア:アパレルプレス",
//    		    "コンサルタント・士業・通訳:公認会計士",
//    		    "コンサルタント・士業・通訳:弁理士",
//    		    "コンサルタント・士業・通訳:弁護士",
//    		    "コンサルタント・士業・通訳:行政書士",
//    		    "コンサルタント・士業・通訳:通訳",
//    		    "コンサルタント・士業・通訳:翻訳",
//    		    "コンサルタント・士業・通訳:経営コンサルタント",
//    		    "コンサルタント・士業・通訳:司法書士",
//    		    "金融・不動産:ファイナンシャルプランナー",
//    		    "金融・不動産:不動産開発",
//    		    "金融・不動産:トレーダー",
//    		    "金融・不動産:保険査定",
//    		    "金融・不動産:マンション管理",
    		    "農業・林業・水産業・公務員:役所職員"
//    		    "農業・林業・水産業・公務員:農業",
//    		    "農業・林業・水産業・公務員:林業",
//    		    "農業・林業・水産業・公務員:漁業",
//    		    "農業・林業・水産業・公務員:畜産業",
//    		    "農業・林業・水産業・公務員:酪農家",
//    		    "農業・林業・水産業・公務員:園芸",
//    		    "ナイトワーク:キャバクラ",
//    		    "ナイトワーク:ホストクラブ",
//    		    "ナイトワーク:フロアレディ",
//    		    "ナイトワーク:クラブ",
//    		    "ナイトワーク:ナイトマネージャー"


    	);
    
    // 乱数生成器
       Random random = new Random();
       
       List<String> joblist = new ArrayList<String>();
       String randomjob = "";
       
       //ランダムな数を生成 (0からdayPeriod-1)
      
       int jobIndex = 0;
       for(int i=0;i<=rIndex;i++) {
      	 jobIndex = random.nextInt(list.size());
       	randomjob = list.get(jobIndex);
       	joblist.add(randomjob);
       }
       
       return joblist;
    
		 }
    
    public List<String> Randomyears(Integer rIndex) {

    	//Integer[]型 {}を配列型[a,b,c]に直してる
    	List<String> yearslist = new ArrayList<String>();
   // 乱数生成器
      Random random = new Random();
    //ランダムな数を生成 (0からdayPeriod-1)
      
      Integer randomyear = null;
      
      for(Integer i=0;i<=rIndex;i++) {

      randomyear = random.nextInt(50);
      yearslist.add(randomyear+"年");
      
     }
      
      return yearslist;
    }
    
    public String randomChangeJobday() {

      List<String> list = new ArrayList<String>();
      Collections.addAll(list,"半年以内","1年以内","良い機会があれば","今は就職を考えていない");
   // 乱数生成器
      Random random = new Random();
    //ランダムな数を生成 (0からdayPeriod-1)
      Integer randomIndex = random.nextInt(4);
      
      String randomChangeJobday = list.get(randomIndex);
      
      return randomChangeJobday;
    }
    
    public String randomEmploymentType() {
    	List<String> list = new ArrayList<String>();
        Collections.addAll(list,
        		"フルタイム",
        	    "パート",
        	    "契約社員",
        	    "派遣社員",
        	    "インターン");
     // 乱数生成器
        Random random = new Random();
      //ランダムな数を生成 (0からdayPeriod-1)
        Integer randomIndex = random.nextInt(5);
        
        String randomValue = list.get(randomIndex);
        
        return randomValue;
    }
    
    public String randomValueGenerate(List<String> list,Integer randomInt) {

     // 乱数生成器W
        Random random = new Random();
      //ランダムな数を生成 (0からdayPeriod-1)
        Integer randomIndex = random.nextInt(randomInt);
        
        String randomValue = list.get(randomIndex);
        
        return randomValue;
    }
    
}
