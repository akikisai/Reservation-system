package reserve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReservationCheck {
	public String CharacterCheck(int nowYear, int nowMonth, int nowDay) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (;;) {
			//入力受付（ENDの場合、モード画面に移動）
			System.out.println(">日付を入力して下さい(" + nowYear + "/MM/dd)、ENDと入力するとモード指定に戻ります）");
			String dateStr = br.readLine();
			System.out.println();

			// 入力値内に数値以外の文字があるかどうかの判定(true :文字がある　false：数値のみ)
			boolean result = false;
			for (int i = 0; i < dateStr.length(); i++) {
				if (Character.isDigit(dateStr.charAt(i))) {
					continue;
				} else {
					result = true;
					break;
				}
			}
			if (dateStr.equals("END") || dateStr.equals("end")) {
				return "0";
			}

			// 日付取得
			int year, month, day;

			if (dateStr.length() == 8) { // (if4)    for1-if1-for2-if4
				//	8文字の場合

				char a = dateStr.charAt(4);
				char b = dateStr.charAt(6);
				char c = '/';
				char f = ' ';

				if (result == true) {

					try {
						year = Integer.parseInt(dateStr.substring(0, 4));
						month = Integer.parseInt(dateStr.substring(5, 6));
						day = Integer.parseInt(dateStr.substring(7, 8));
						//			各変数に対応する値を入力値から取得→格納

						if (a == c && b == c || a == f && b == f) {//入力値の指定位置に"/"が存在するかどうかの判定
						} else { //エラー処理
							System.out.println("区切り文字は /を使ってください");
							continue;
						}

					} catch (Exception e) {
						//		エラー処理
						System.out.println("日付を入力してください");
						System.out.println(">ひらがなや漢字は使えません。整数で入力してください。");
						continue;
					}
				}

				else if (result == false) { //(if5)     for1-if1-for2-if4-if5
					//		 全て数値かどうか判定
					try {
						year = Integer.parseInt(dateStr.substring(0, 4));
						month = Integer.parseInt(dateStr.substring(4, 6));
						day = Integer.parseInt(dateStr.substring(6, 8));
						//			用意した各変数に対応する値を入力値から取得→格納

					} catch (Exception e) {
						//		エラー処理
						System.out.println("日付を入力してください");

						continue;
					}
				} else if (a != c && b != c) {
					//			エラー処理
					System.out.println("日付を入力してください");
					System.out.println(">ひらがなや漢字は使えません。整数で入力してください。");
					continue;

				} else {
					System.out.println("日付を入力してください");
					continue;

				}
			} else if (dateStr.length() == 9) { //(if1)  for1-if1

				char a = dateStr.charAt(4);
				char b = dateStr.charAt(6);
				char d = dateStr.charAt(7);
				char c = '/';
				char f = ' ';

				if (result == false) { //(if2)   for1-if1-if2

					continue;
				}

				else if (result == true) {

					if (b == c && d != c || b == f && d != f) { //(if3)   for1-if1-if2-if3
						try {
							year = Integer.parseInt(dateStr.substring(0, 4));
							month = Integer.parseInt(dateStr.substring(5, 6));
							day = Integer.parseInt(dateStr.substring(7, 9));
							//			各変数に対応する値を入力値から取得→格納

							if (a == c && b == c || a == f && b == f) {//入力値の指定位置に"/"が存在するかどうかの判定    //(if4)    for1-if1-if2-if3-if4
							} else { //エラー処理
								System.out.println("区切り文字は /を使ってください");
								continue;
							} //if4 end

						} catch (Exception e) {
							//		エラー処理
							System.out.println("日付を入力してください");
							System.out.println(">ひらがなや漢字は使えません。整数で入力してください。");
							continue;
						}
					} //if3  end
					else if (d == c && b != c || d == f && b != f) { //(if5)   for1-if1-if2-if5
						try {
							year = Integer.parseInt(dateStr.substring(0, 4));
							month = Integer.parseInt(dateStr.substring(5, 7));
							day = Integer.parseInt(dateStr.substring(8, 9));
							//			各変数に対応する値を入力値から取得→格納

							if (a == c && d == c || a == f && d == f) {//入力値の指定位置に"/"が存在するかどうかの判定    (if6)  for1-if1-if2-if5-if6
							} else { //エラー処理
								System.out.println("区切り文字は /を使ってください");
								continue;
							} //if6 end

						} catch (Exception e) {
							//		エラー処理
							System.out.println("日付を入力してください");
							System.out.println(">ひらがなや漢字は使えません。整数で入力してください。");
							continue;
						}
					} else {
						//		エラー処理
						System.out.println("日付を入力してください");
						System.out.println(">ひらがなや漢字は使えません。整数で入力してください。");

						continue;

					} //if5 end
				} else {
					continue;
				} //if2 end

			} else if (dateStr.length() == 10) {

				char a = dateStr.charAt(4);
				char b = dateStr.charAt(7);
				char c = '/';
				char f = ' ';

				if (result == true) {

					try {
						year = Integer.parseInt(dateStr.substring(0, 4));
						month = Integer.parseInt(dateStr.substring(5, 7));
						day = Integer.parseInt(dateStr.substring(8, 10));
						//			各変数に対応する値を入力値から取得→格納

						if (a == c && b == c || a == f && b == f) {//入力値の指定位置に"/"が存在するかどうかの判定
						} else { //エラー処理
							System.out.println("区切り文字は /を使ってください");
							continue;
						}

					} catch (Exception e) {
						//		エラー処理
						System.out.println("日付を入力してください");
						System.out.println(">ひらがなや漢字は使えません。整数で入力してください。");
						continue;
					}
				} else {
					//		エラー処理
					System.out.println("区切り文字は /を使ってください");
					continue;
				}
			} else if (dateStr.length() == 0) {
				//	0文字の場合の処理
				System.out.println("文字数が足りません。正しい日付で入力してください");
				continue;
			} else {
				//	8文字でも10文字でもない場合の処理
				//	全て数値型の場合
				if (result == false) {
					System.out.println("YYYYMMDDまたはYYYY/MM/DDで入力してください");
					continue;
				} else {
					System.out.println("日付を入力してください");
					System.out.println(">ひらがなや漢字,英字は使えません。整数で入力してください。");
					continue;
				}
			}

			//月日の妥当性確認
			if (year < nowYear || month < 1 || month > 12 || day < 1 || day > 31) {

				System.out.println(">入力された日付が正しくありません。");
				System.out.println(">2021年の本日以降の日付で入力ください。");
				continue;
			}
			//現在の日付と入力した日付を比較して前の月の場合、日付入力に戻る
			if (year == nowYear && month < nowMonth) {

				System.out.println("過去の日付は確認できません。");
				continue;
			}
			//現在の日付と入力した日付を比較して同じ月で前の日の場合、日付入力に戻る
			if (year == nowYear && month == nowMonth && day < nowDay) {

				System.out.println("過去の日付は確認できません。");
				continue;
			}else  if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ) {
				if(month == 2 && day >= 30) {
						System.out.println(">入力された日付が正しくありません。");
				continue;
					}
			}else if(year % 4 != 0 && year % 100 == 0 || year % 400 != 0) {
				if(month == 2 && day >= 29) {
					System.out.println(">入力された日付が正しくありません。");
			continue;
				}
			}
			 if(month == 4 && day >= 31
					|| month == 6 && day >= 31
					|| month == 9 && day >= 31
					|| month == 11 && day >= 31) {
				System.out.println(">入力された日付が正しくありません。");
				continue;
			}

			String Syear = String.valueOf(year);
			String Smonth = String.valueOf(month);
			String Sday = String.valueOf(day);
			if (month >= 1 && month < 10) {
				String SSmonth = "0".concat(Smonth);
				if (day >= 1 && day < 10) {
					String SSday = "0".concat(Sday);
					String YM = Syear.concat(SSmonth);
					String YMD = YM.concat(SSday);
					return YMD;
				} else {
					String YM = Syear.concat(SSmonth);
					String YMD = YM.concat(Sday);
					return YMD;
				}

			} else {
				if (day >= 1 && day < 10) {
					String SSday = "0".concat(Sday);
					String YM = Syear.concat(Smonth);
					String YMD = YM.concat(SSday);
					return YMD;
				} else {
					String YM = Syear.concat(Smonth);
					String YMD = YM.concat(Sday);

					return YMD;
				}
			}
		}

	}
}
