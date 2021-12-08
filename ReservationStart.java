package reserve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationStart {

	public static int[][] memory = new int[365][7];
	public static String[][][] memory2 = new String[365][7][50];

	public static void main(String[] args) throws IOException {

		//　memory初期化
		memory = ReservationMemory.initData();

		LocalDate localDate = LocalDate.now();

		//現在の日付を取得
		int nowYear, nowMonth, nowDay;
		nowYear = localDate.getYear();
		nowMonth = localDate.getMonthValue();
		nowDay = localDate.getDayOfMonth();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (;;) {

			System.out.println(">モードを指定してください");
			System.out.println(">1:日付指定(当日のみ)");
			System.out.println(">2:日付指定(指定日から1週間)");
			System.out.println(">3:日付指定(当日表)");
			System.out.println(">4:日付指定(予約)");
			System.out.println(">5:日付指定(予約確認)");
			System.out.println(">6:日付指定(カレンダー)");

			// 入力したモード
			String selectStr;
			int select = -1;

			try {
				selectStr = br.readLine();
				select = Integer.parseInt(selectStr);
			} catch (Exception e) {
				System.out.println("1,2,3,4,5で入力してください");
				continue;
			}

			//日付指定モード
			if (select == 1) {

				for (;;) {
					ReservationCheck RC = new ReservationCheck();
					String InDate = RC.CharacterCheck(nowYear, nowMonth, nowDay);

					if (InDate == "0") {
						break;
					}

					int year = Integer.parseInt(InDate.substring(0, 4));
					int month = Integer.parseInt(InDate.substring(4, 6));
					int day = Integer.parseInt(InDate.substring(6, 8));

					DayCheck daycheck = new DayCheck();
					int dayOfYear = daycheck.GetDate(year, month, day);
					String dayOfWeekStr = daycheck.GetDay(year, month, day);

					//日に座席が一つでもあるかどうか
					boolean existSeatInDay = false;
					//日の全時間帯に座席が無いか求める
					for (int i = 0; i < memory[i].length; i++) {

						//時間帯の座席数
						int remain = memory[dayOfYear - 1][i];

						//座席が存在するとき
						if (remain > 0) {
							existSeatInDay = true;
							break;
						}
					}
					if (existSeatInDay == false) {
						System.out.println(year + "/" + month + "/" + day + dayOfWeekStr + "に残った席がありません。\n");
						continue;
					}

					// 指定日の予約状態を表示
					System.out.println(">◎:余裕あり　○:空きあり　△:残りわずか　ー:満席");
					System.out.println(">" + year + "/" + month + "/" + day + dayOfWeekStr + "の予約状態は以下の通りです。");
					for (int i = 0; i < memory[i].length; i++) {
						//残った席数
						int remain = memory[dayOfYear - 1][i];

						//表示
						if (i == 0) {
							System.out.print(">09:00 ");
						} else if (i == 1) {
							System.out.print(">10:00 ");
						} else if (i == 2) {
							System.out.print(">11:00 ");
						} else if (i == 3) {
							System.out.print(">13:00 ");
						} else if (i == 4) {
							System.out.print(">14:00 ");
						} else if (i == 5) {
							System.out.print(">15:00 ");
						} else if (i == 6) {
							System.out.print(">16:00 ");
						}
						System.out.println(getStatusStr(remain));

					}
					System.out.println();
				}

				// モード２（1週間分の予定を表示）
			} else if (select == 2) {
				for (;;) {
					ReservationCheck RC = new ReservationCheck();
					String InDate = RC.CharacterCheck(nowYear, nowMonth, nowDay);

					if (InDate == "0") {
						break;
					}

					int year = Integer.parseInt(InDate.substring(0, 4));
					int month = Integer.parseInt(InDate.substring(4, 6));
					int day = Integer.parseInt(InDate.substring(6, 8));

					//7日の予約を表示
					int dayOfYear = -1;
					for (int j = 0; j < 7; j++) {
						//	//指定日（始める日）
						if (j == 0) {
							localDate = LocalDate.now();

							try {
								localDate = LocalDate.of(year, month, day);
							} catch (DateTimeException e) {
								// エラー処理
								System.out.println(">入力された日付が正しくありません。");
								break;
							}

							//		//dayOfYearの範囲：1 - 365
							dayOfYear = localDate.getDayOfYear();

							//	//指定日以降
						} else {
							//		//1年の最後以降は出力なしのため
							if (dayOfYear >= 365) {
								dayOfYear = -1;
								break;
							}
							dayOfYear++;
							//
							localDate = LocalDate.ofYearDay(year, dayOfYear);
							year = localDate.getYear();
							month = localDate.getMonthValue();
							day = localDate.getDayOfMonth();
						}

						DayCheck daycheck = new DayCheck();

						String dayOfWeekStr = daycheck.GetDay(year, month, day);

						//	//日に座席が一つでもあるかどうか
						boolean existSeatInDay = false;
						//	//日の全時間帯に座席が無いか求める
						for (int i = 0; i < memory[i].length; i++) {

							//時間帯の座席数
							int remain = memory[dayOfYear - 1][i];

							//座席が存在するとき
							if (remain > 0) {
								existSeatInDay = true;
								break;
							}
						}
						if (existSeatInDay == false) {
							System.out.println(year + "/" + month + "/" + day + dayOfWeekStr + "に残った席がありません。\n");
							continue;
						}

						//	// 指定日の予約状態を表示

						System.out.println(">◎:余裕あり　○:空きあり　△:残りわずか　ー:満席");
						System.out.println(">" + year + "/" + month + "/" + day + dayOfWeekStr + "の予約状態は以下の通りです。");
						for (int i = 0; i < memory[i].length; i++) {
							//残った席数
							int remain = memory[dayOfYear - 1][i];

							//表示
							if (i == 0) {
								System.out.print(">09:00 ");
							} else if (i == 1) {
								System.out.print(">10:00 ");
							} else if (i == 2) {
								System.out.print(">11:00 ");
							} else if (i == 3) {
								System.out.print(">13:00 ");
							} else if (i == 4) {
								System.out.print(">14:00 ");
							} else if (i == 5) {
								System.out.print(">15:00 ");
							} else if (i == 6) {
								System.out.print(">16:00 ");
							}
							System.out.println(getStatusStr(remain));

							System.out.println();
						}

					}
				}
			} else if (select == 3) {
				for (;;) {

					ReservationCheck RC = new ReservationCheck();
					String InDate = RC.CharacterCheck(nowYear, nowMonth, nowDay);

					if (InDate == "0") {
						break;
					}
					int year = Integer.parseInt(InDate.substring(0, 4));
					int month = Integer.parseInt(InDate.substring(4, 6));
					int day = Integer.parseInt(InDate.substring(6, 8));

					DayCheck daycheck = new DayCheck();

					int dayOfYear = daycheck.GetDate(year, month, day);
					String dayOfWeekStr = daycheck.GetDay(year, month, day);

					String t0, t1, t2, t3, t4, t5, t6;
					t0 = t1 = t2 = t3 = t4 = t5 = t6 = "";

					for (int i = 0; i < memory[i].length; i++) {
						//残った席数
						int remain = memory[dayOfYear - 1][i];

						//						t1 = getStatusStr(remain);
						//表示
						if (i == 0) {
							t0 = getStatusStr(remain);
						} else if (i == 1) {
							t1 = getStatusStr(remain);
						} else if (i == 2) {
							t2 = getStatusStr(remain);
						} else if (i == 3) {
							t3 = getStatusStr(remain);
						} else if (i == 4) {
							t4 = getStatusStr(remain);
						} else if (i == 5) {
							t5 = getStatusStr(remain);
						} else if (i == 6) {
							t6 = getStatusStr(remain);
						}

					}

					// 指定日の予約状態を表示
					System.out.println(">◎:余裕あり　○:空きあり　△:残りわずか　ー:満席");
					System.out.println(">" + year + "/" + month + "/" + day + dayOfWeekStr + "の予約状態は以下の通りです。");
					System.out.println("> +－--------－+------------------------------------------------------+");
					System.out.println("> |   日付     | 9:00 | 10:00 | 11:00 | 13:00 | 14:00 | 15:00 | 16:00 |");
					System.out.println("> |－--------－+------+-------+-------+-------+-------+-------+-------+");
					String scheduleStr = String.format(
							"> |  %2d日%s  |  %-2s |  %-2s  |  %-2s  |  %-3s |  %-3s |  %-2s  |  %-3s |",
							day, dayOfWeekStr, t0, t1, t2, t3, t4, t5, t6);
					System.out.println(scheduleStr);
					System.out.println("> +－--------－+------+-------+-------+-------+-------+-------+-------+");

					String.format("%2d日(%s)", day, dayOfWeekStr);

				}
			} else if (select == 4) {
				for (;;) {
					ReservationCheck RC = new ReservationCheck();
					String InDate = RC.CharacterCheck(nowYear, nowMonth, nowDay);

					if (InDate == "0") {
						break;
					}
					int year = Integer.parseInt(InDate.substring(0, 4));
					int month = Integer.parseInt(InDate.substring(4, 6));
					int day = Integer.parseInt(InDate.substring(6, 8));

					DayCheck daycheck = new DayCheck();

					int dayOfYear = daycheck.GetDate(year, month, day);
					String dayOfWeekStr = daycheck.GetDay(year, month, day);

					String t0, t1, t2, t3, t4, t5, t6;
					t0 = t1 = t2 = t3 = t4 = t5 = t6 = "";

					for (int i = 0; i < memory[i].length; i++) {
						//残った席数
						int remain = memory[dayOfYear - 1][i];

						t1 = getStatusStr(remain);
						//表示
						if (i == 0) {
							t0 = getStatusStr(remain);
						} else if (i == 1) {
							t1 = getStatusStr(remain);
						} else if (i == 2) {
							t2 = getStatusStr(remain);
						} else if (i == 3) {
							t3 = getStatusStr(remain);
						} else if (i == 4) {
							t4 = getStatusStr(remain);
						} else if (i == 5) {
							t5 = getStatusStr(remain);
						} else if (i == 6) {
							t6 = getStatusStr(remain);
						}

					}

					// 指定日の予約状態を表示
					System.out.println(">◎:余裕あり　○:空きあり　△:残りわずか　ー:満席");
					System.out.println(">" + year + "/" + month + "/" + day + dayOfWeekStr + "の予約状態は以下の通りです。");
					System.out.println("> +－--------－+------------------------------------------------------+");
					System.out.println("> |   日付     | 9:00 | 10:00 | 11:00 | 13:00 | 14:00 | 15:00 | 16:00 |");
					System.out.println("> |－--------－+------+-------+-------+-------+-------+-------+-------+");
					String scheduleStr = String.format(
							"> |  %2d日%s  |  %-2s |  %-2s  |  %-2s  |  %-3s |  %-3s |  %-2s  |  %-3s |",
							day, dayOfWeekStr, t0, t1, t2, t3, t4, t5, t6);
					System.out.println(scheduleStr);
					System.out.println("> +－--------－+------+-------+-------+-------+-------+-------+-------+");

					String.format("%2d日(%s)", day, dayOfWeekStr);

					//予約
					System.out.println("予約する時間帯のを入力してください。(入力例：9:00)");
					for (;;) {

						//入力した時間帯の席数をremain2に代入
						int remain2 = 0;
						int t = -1;
						int number = 0;
						for (;;) {
							String n1 = br.readLine();
							if (n1.equals("9:00")) {
								remain2 = memory[dayOfYear - 1][0];
								t = 0;
								break;
							} else if (n1.equals("10:00")) {
								remain2 = memory[dayOfYear - 1][1];
								t = 1;
								break;
							} else if (n1.equals("11:00")) {
								remain2 = memory[dayOfYear - 1][2];
								t = 2;
								break;
							} else if (n1.equals("13:00")) {
								remain2 = memory[dayOfYear - 1][3];
								t = 3;
								break;
							} else if (n1.equals("14:00")) {
								remain2 = memory[dayOfYear - 1][4];
								t = 4;
								break;
							} else if (n1.equals("15:00")) {
								remain2 = memory[dayOfYear - 1][5];
								t = 5;
								break;
							} else if (n1.equals("16:00")) {
								remain2 = memory[dayOfYear - 1][6];
								t = 6;
								break;
							} else {
								System.out.println("入力が間違っています。入力しなおしてください。");
								continue;
							}

						}

						if (getStatusStr(remain2).equals("ー")) {
							System.out.println("満席です。ほかの時間帯を指定してください。");
							continue;
						} else {
							String[] jikantai = { "9:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00" };
							System.out.println("名前を入力してください。");
							String name = br.readLine();

							for (int i = 0; i < 50; i++) {
								if (memory2[dayOfYear - 1][t][i] != null) {
									continue;
								} else {
									memory2[dayOfYear - 1][t][i] = name;
									System.out.println("予約できました、以下の情報を確認してください。");
									System.out.println("名前: 　　　" + name + "\n"
											+ "予約番号:　 " + (i + 1) + "\n"
											+ "予約日付:　 " + year + "年" + month + "月" + day + "日" + jikantai[t] + "～\n");
									number = i + 1;
									break;
								}
							}
							memory[dayOfYear - 1][t] -= 1;
							System.out.println("予約できました。\n");

							try {
								FileWriter file = new FileWriter(
										"C:\\Users\\internship4\\Desktop\\java\\reservation.txt", true);
								PrintWriter pw = new PrintWriter(new BufferedWriter(file));
								pw.println(
										year + "," + month + "," + day + "," + number + "," + jikantai[t] + "," + name);

								pw.close();

							} catch (IOException e) {
								e.printStackTrace();
							}

							break;
						}
					}
					break;
				}
			} else if (select == 5) {

				System.out.println("予約した名前を入力してください。");
				String name = br.readLine();
				List<String> list = new ArrayList<String>();
				String[] a = new String[6];
				int t = 0;

				try {
					// ファイルのパスを指定する
					File file = new File("C:\\Users\\internship4\\Desktop\\java\\reservation.txt");

					// ファイルが存在しない場合に例外が発生するので確認する
					if (!file.exists()) {
						System.out.print("ファイルが存在しません");
						return;
					}

					// BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					String data;
					while ((data = bufferedReader.readLine()) != null) {
						a = data.split(",");
						list.add(a[0]);
						list.add(a[1]);
						list.add(a[2]);
						list.add(a[3]);
						list.add(a[4]);
						list.add(a[5]);

						a[0] = null;
						a[1] = null;
						a[2] = null;
						a[3] = null;
						a[4] = null;
						a[5] = null;
					}
					for (int k = 0; k < list.size(); k++) {
						if (list.get(k).equals(name)) {
							System.out.println("名前: 　　　" + list.get(k) + "\n"
									+ "予約番号:　 " + list.get(k - 2) + "\n"
									+ "予約日付:　 " + list.get(k - 5) + "年" + list.get(k - 4) + "月" + list.get(k - 3) + "日"
									+ list.get(k - 1) + "～\n");

							System.out.println("\n\n");
							t = 1;
							break;

						}
					}
					if (t == 0) {
						System.out.println("この名前で予約されていません。");
					}

					// 最後にファイルを閉じてリソースを開放する
					bufferedReader.close();

				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

			} else if (select == 6) {

				System.out.println("西暦を入力してください");
				String seireki = br.readLine();
				select = Integer.parseInt(seireki);
				int a = select - 2021; //aは入力された西暦
				System.out.println("月を入力してください");
				String tuki = br.readLine();
				select = Integer.parseInt(tuki);
				int b = select; //bは入力された月      //切り取りなし　終わり

				int thisYear = Calendar.getInstance().get(Calendar.YEAR); //切り取りなし　始め

				System.out.println("☆: 残り席　221～350");
				System.out.println("◎: 残り席　171～220");
				System.out.println("〇: 残り席　121～170");
				System.out.println("△: 残り席　1～120");
				System.out.println("×: 残り席　0");
				System.out.println();

				System.out.println(thisYear + a + "年" + b + "月");
				String[] weeks = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
				for (String s : weeks) {
					System.out.printf("%4s", s);
				}
				System.out.println();
				new SampleCalendar(a+thisYear, b);
				SampleCalendar.calc();

			} else {
				System.out.println(">入力した整数が間違っています。1,2,3,4,5いずれかの数字を入力してください。");
			}
		}

	}

	//残席を入力して、「◎:余裕あり　○:空きあり　△:残りわずか　ー:満席」をリターン
	private static String getStatusStr(int remain) {
		if (remain >= 20) {
			return "◎";
		} else if (remain >= 10) {
			return "○";
		} else if (remain > 0) {
			return "△";
		} else {
			return "ー";
		}
	}

}
