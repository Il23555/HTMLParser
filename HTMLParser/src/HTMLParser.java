import java.io.*;
import java.net.URL;
import java.nio.channels.*;
import java.util.HashMap;

public class HTMLParser {

    /** Скачивание HTML-файла по URL */
    public void downloadHTMLFile(String url, String fileName) throws Exception {
        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        readableByteChannel.close();
        fileOutputStream.close();
        fileChannel.close();
    }

    /** Чтение HTML-файла */
    public String readHTMLFile(String fileName){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /** Редактивание HTML-текста: удаление тегов, знаков пунктуаций, лишних пробелов */
    public String editHTMLText(String text){
        text = text.replaceAll("<head.+?</head>|<script.+?</script>|<style.+?</style>|<.+?>|&nbsp;+"," ")
                .replaceAll("([,.?!:;\"\\[\\]\\(\\)])", " ")
                .replaceAll("[\r\n\t]+"," ")
                .replaceAll("\\s+", " ")
                .toUpperCase();

        return text;
    }

    /** Подсчет вхождений уникальных слов в текст */
    public HashMap<String,Integer> countWords(String text){
        String[] words = text.split("\\s+");
        HashMap<String,Integer> map = new HashMap<>();
        for (String word: words)
            map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
        return map;
    }
}
