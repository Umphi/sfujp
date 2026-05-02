import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    String inputText = "";

    int lowercaseCountCyrillic = 0;
    int uppercaseCountCyrillic = 0;
    int lowercaseCountLatin = 0;
    int uppercaseCountLatin = 0;
    int punctuationMarksCount = 0;
    int spacesCount = 0;

    public String getText() {
        return this.inputText;
    }

    public void setText(String text) {
        this.inputText = text;

        this.lowercaseCountCyrillic = (int) text.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC).filter(c -> Character.isLowerCase(c)).count();
        this.uppercaseCountCyrillic = (int) text.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC).filter(c -> Character.isUpperCase(c)).count();
        this.lowercaseCountLatin = (int) text.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN).filter(c -> Character.isLowerCase(c)).count();
        this.uppercaseCountLatin = (int) text.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN).filter(c -> Character.isUpperCase(c)).count();
        this.punctuationMarksCount = (int) text.chars().filter(c -> ".,!?;:-()\"'«»…".indexOf(c) != -1).count();
        this.spacesCount = (int) text.chars().filter(Character::isWhitespace).count();
    }

    public int getLowerCyrillic() {
        return this.lowercaseCountCyrillic;
    }
    public int getUpperCyrillic() {
        return this.uppercaseCountCyrillic;
    }
    public int getLowerLatin() {
        return this.lowercaseCountLatin;
    }
    public int getUpperLatin() {
        return this.uppercaseCountLatin;
    }
    public int getPunctuationCount() {
        return this.punctuationMarksCount;
    }
    public int getSpacesCount() {
        return this.spacesCount;
    }

    public static String generateRandomString(int maxLength) {
        Random random = new Random();
        int length = random.nextInt(maxLength + 1);
        
        String cyrillic = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String latin = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String punctuation = ".,!?;:-()\"'«»…";
        String spaces = " ";
        
        String allChars = cyrillic + latin + punctuation + spaces;
        
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allChars.length());
            result.append(allChars.charAt(index));
        }
        return result.toString();
    }

    public void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("\n".repeat(50));

            System.out.print("Текущий текст: %s\nСтрочная кириллица: %d\nПрописная кириллица: %d\nСтрочная латиница: %d\nПрописная латиница: %d\nЗнаков препинания: %d\nПробелов: %d\n 1. Ввести новый текст вручную\n 2. Сгенерировать случайный текст\n 3. Описание программы\n 4. Выход\n".formatted(this.getText(), this.getLowerCyrillic(), this.getUpperCyrillic(), this.getLowerLatin(), this.getUpperLatin(), this.getPunctuationCount(), this.getSpacesCount()));
            
            String input = "";

            while (!"1".equals(input) && !"2".equals(input) && !"3".equals(input) && !"4".equals(input)) {
                System.out.print("Выберите пункт меню: ");
                input = br.readLine();
            }
            
            switch(input) {
                case "1":
                    System.out.print("Введите текст: ");
                    this.setText(br.readLine());
                    break;
                case "2":
                    this.setText(generateRandomString(100));
                    break;
                case "3":
                    System.out.print("Данная программа принимает текст в качестве входных данных (пользовательский ввод, либо генерация случайной последовательности). Результатом работы программы является вывод количества строчных и прописных букв для каждого из алфавитов, а также числа знаков препинания и пробелов.\nПрограмму выполнил студент группы ЗКИ25-18Б Юркевич И.А.\nДля продолжения нажмите Enter...");
                    br.readLine();
                    break;
                case "4":
                    return;
            }
        }
    }
}
