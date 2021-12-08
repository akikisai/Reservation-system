package reserve;

/**
 * 1年間の座席数格納
 */

public class ReservationMemory {
//	MemoryArr[dayOfYear][時間帯] = 残った座席数

	//	day of year（1 - 365)
	//	1月1日 - 1		格納は0番
	//	12月31日 - 365	格納は364番

	//	時間帯（7つ）
	//	＞9:00  0
	//	＞10:00 1
	//	＞11:00 2
	//	＞13:00 3
	//	＞14:00 4
	//	＞15:00 5
	//	＞16:00 6

	//予約状況
	int[][] data = new int[365][7];

	/**
	 * 予約状況初期化
	 */
	public static int[][] initData() {

		int[][] data = new int[365][7];

		//残った席数をrandomに格納
		for (int i = 0; i < 365; i++) {
			for (int j = 0; j < 7; j++) {
				//キャパは50
				int randNum = (int) (Math.random() * 51);
				data[i][j] = randNum;
			}
		}

		return data;
	}
}
