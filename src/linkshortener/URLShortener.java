package linkshortener;

import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private Map<String, String> urlMap;
    private static final String BASE_URL = "http://short.url/";

    public URLShortener() {
        urlMap = new HashMap<>();
    }

    public String shortenURL(String longURL) {
        if (urlMap.containsValue(longURL)) {
            return urlMap.entrySet()
                         .stream()
                         .filter(entry -> longURL.equals(entry.getValue()))
                         .map(Map.Entry::getKey)
                         .findFirst()
                         .orElse(null);
        }

        String shortURL = BASE_URL + generateUniqueID(longURL);
        urlMap.put(shortURL, longURL);
        return shortURL;
    }

    public String expandURL(String shortURL) {
        if (!urlMap.containsKey(shortURL)) {
            throw new IllegalArgumentException("Invalid short URL");
        }
        return urlMap.get(shortURL);
    }

    private String generateUniqueID(String longURL) {
        return Integer.toHexString(longURL.hashCode());
    }
}
