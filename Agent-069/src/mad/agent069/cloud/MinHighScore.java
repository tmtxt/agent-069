package mad.agent069.cloud;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Preferences;

public class MinHighScore {
	// private static final String MIN_KEY = "minVal";
	private static final String HAS_UPLOADED_KEY = "uploaded";
	private static final String HIGHSCORE_KEY = "highscore";
	public static ArrayList<String[]> list = null;

	private static Preferences prefs = Gdx.app.getPreferences("Highscores");

	public static String processHighscore(String username, int score) {
		if (!isNewHighscore(score)) {
			return "Not your new highscore, try harder!";
		}

		if (!highScoreUsernameExists(username)) {
			uploadFirstHighscore(username, score);
			updateHighscoreList();
		} else {
			System.out.println("NHAN: uploadHighscore() executing...");
			uploadExistingHighscore(username, score);
			updateHighscoreList();
		}

		return "0";
	}

	public static ArrayList<String[]> getHighscoreList() {

		updateHighscoreList();
		ArrayList<String[]> returns = list;

		return returns;
	}

	public static void updateHighscoreList() {

		// Map<String, String> params = new HashMap<String, String>();
		// params.put("order", "-score");
		// params.put("limit", "10");

		HttpRequest httpGet = new HttpRequest(HttpMethods.GET);

		// httpGet.setContent(HttpParametersUtils.convertHttpParameters(params));

		httpGet.setUrl("https://api.parse.com/1/classes/GameScore/?order=-score&limit=10");
		httpGet.setHeader("X-Parse-Application-Id",
				"MUjViympEGbNnn6U9kKSQxFcSbflzNw2vKXTj5zq");
		httpGet.setHeader("X-Parse-REST-API-Key",
				"4bYNmz9OFC7LdiVzeLDAdZtndBeV5aQD6hWDvE6K");

		Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
			public void handleHttpResponse(HttpResponse httpResponse) {

				String result = httpResponse.getResultAsString();
				System.out.println("NHAN: SUCCESS - " + result);
				MinHighScore.list = processHighscoreJSON(result);
			}

			public void failed(Throwable t) {
				System.out.println("NHAN: FAILED while fetching score");
			}
		});

	}

	private static ArrayList<String[]> processHighscoreJSON(String json) {
		ArrayList<String[]> re = new ArrayList<String[]>();
		Pattern p = Pattern
				.compile("\"score\":\\s*([0-9]+),\\s*\"playerName\":\\s*\"([0-9a-zA-Z]+)\"\\s*");
		// Pattern p =
		// Pattern.compile("\"score\":\\s*([0-9]+),\\s*\"playerName\":\\s*\"([0-9a-zA-Z]+)\"");

		Matcher m = p.matcher(json);

		while (m.find()) {
			String player = m.group(1);
			String score = m.group(2);
			String[] item = { player, score };
			re.add(item);
			System.out.println("NHAN: " + m.group(1) + "__" + m.group(2));
		}

		// prefs.flush();
		return re;
	}

	// TODO update this
	private static void uploadExistingHighscore(String username, final int score) {

		String json = "{\"score\":" + score + ",\"playerName\":\"" + username
				+ "\"}";

		HttpRequest httpGet = new HttpRequest(HttpMethods.POST);

		System.out.println("NHAN: " + json);
		httpGet.setContent(json);

		httpGet.setUrl("https://api.parse.com/1/classes/GameScore");
		httpGet.setHeader("X-Parse-Application-Id",
				"MUjViympEGbNnn6U9kKSQxFcSbflzNw2vKXTj5zq");
		httpGet.setHeader("X-Parse-REST-API-Key",
				"4bYNmz9OFC7LdiVzeLDAdZtndBeV5aQD6hWDvE6K");
		httpGet.setHeader("Content-Type", "application/json");

		Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
			public void handleHttpResponse(HttpResponse httpResponse) {
				System.out.println("NHAN: SUCCESS - "
						+ httpResponse.getResultAsString());
			}

			public void failed(Throwable t) {
				System.out.println("NHAN: FAILED while uploading score");
			}
		});

		prefs.putInteger(HIGHSCORE_KEY, score);
		prefs.flush();
	}

	private static boolean highScoreUsernameExists(String username) {
		return prefs.getBoolean(HAS_UPLOADED_KEY, false);
	}

	private static boolean isNewHighscore(int score) {
		int prefScore = prefs.getInteger(HIGHSCORE_KEY, 0);
		System.out.println("NHAN: prefScore = " + prefScore);
		return prefScore < score;
	}

	private static void uploadFirstHighscore(String username, final int score) {

		String json = "{\"score\":" + score + ",\"playerName\":\"" + username
				+ "\"}";

		HttpRequest httpGet = new HttpRequest(HttpMethods.POST);

		System.out.println("NHAN: " + json);
		httpGet.setContent(json);

		httpGet.setUrl("https://api.parse.com/1/classes/GameScore");
		httpGet.setHeader("X-Parse-Application-Id",
				"MUjViympEGbNnn6U9kKSQxFcSbflzNw2vKXTj5zq");
		httpGet.setHeader("X-Parse-REST-API-Key",
				"4bYNmz9OFC7LdiVzeLDAdZtndBeV5aQD6hWDvE6K");
		httpGet.setHeader("Content-Type", "application/json");

		Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
			public void handleHttpResponse(HttpResponse httpResponse) {

				System.out.println("NHAN: SUCCESS - "
						+ httpResponse.getResultAsString());
			}

			public void failed(Throwable t) {
				System.out.println("NHAN: FAILED while uploading score");
			}
		});
		prefs.putInteger(HIGHSCORE_KEY, score);
		prefs.flush();
	}

	//
	// public static int getMinHighScore() {
	//
	// HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
	// httpGet.setUrl("https://api.parse.com/1/classes/MinHighScore");
	// httpGet.setHeader("X-Parse-Application-Id",
	// "MUjViympEGbNnn6U9kKSQxFcSbflzNw2vKXTj5zq");
	// httpGet.setHeader("X-Parse-REST-API-Key",
	// "4bYNmz9OFC7LdiVzeLDAdZtndBeV5aQD6hWDvE6K");
	//
	// Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
	// public void handleHttpResponse(HttpResponse httpResponse) {
	// int min = getMinVal(httpResponse.getResultAsString());
	// prefs.putInteger(MIN_KEY, min);
	// }
	//
	// public void failed(Throwable t) {
	// System.out.println("NHAN: Internet connection FAILED");
	// }
	// });
	//
	// return prefs.getInteger(MIN_KEY);
	// }

	// private static int getMinVal(String json) {
	// int re = 0;
	//
	// Pattern p = Pattern.compile("\"minVal\":([0-9]{1,}),");
	// Matcher m = p.matcher(json);
	// if (m.find()) {
	// String num = m.group(1);
	// re = Integer.parseInt(num);
	// } else {
	// System.out
	// .println("Something really wrong happened: Malformed json response!");
	// }
	//
	// return re;
	// }

	// public static void uploadScore(String username, int score) {
	//
	// Map<String, String> parameters = new HashMap<String, String>();
	// parameters.put("playerName", username);
	// parameters.put("score", (new Integer(score)).toString());
	//
	// HttpRequest httpGet = new HttpRequest("PUT");
	//
	// httpGet.setContent(HttpParametersUtils.convertHttpParameters(parameters));
	//
	// httpGet.setUrl("https://api.parse.com/1/classes/GameScore");
	// httpGet.setHeader("X-Parse-Application-Id",
	// "MUjViympEGbNnn6U9kKSQxFcSbflzNw2vKXTj5zq");
	// httpGet.setHeader("X-Parse-REST-API-Key",
	// "4bYNmz9OFC7LdiVzeLDAdZtndBeV5aQD6hWDvE6K");
	//
	// Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
	// public void handleHttpResponse(HttpResponse httpResponse) {
	// // int min = getMinVal(httpResponse.getResultAsString());
	// // prefs.putInteger(MIN_KEY, min);
	// System.out.println("NHAN: SUCCESS - " +
	// httpResponse.getResultAsString());
	// }
	//
	// public void failed(Throwable t) {
	// System.out.println("NHAN: FAILED while uploading score");
	// }
	// });
	// }
}
