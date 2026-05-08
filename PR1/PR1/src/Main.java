import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    private String inputText = "";

    private boolean isUpdated = false;
    private int lowercaseCountCyrillic = 0;
    private int uppercaseCountCyrillic = 0;
    private int lowercaseCountLatin = 0;
    private int uppercaseCountLatin = 0;
    private int punctuationMarksCount = 0;
    private int spacesCount = 0;

    public String getText() {
        return this.inputText;
    }

    public void setText(String text) {
        this.inputText = text;

        this.isUpdated = false;
        this.lowercaseCountCyrillic = 0;
        this.uppercaseCountCyrillic = 0;
        this.lowercaseCountLatin = 0;
        this.uppercaseCountLatin = 0;
        this.punctuationMarksCount = 0;
        this.spacesCount = 0;
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
    
    public boolean getIsUpdated() {
        return this.isUpdated;
    }

    public void countParameters() {
        this.lowercaseCountCyrillic = (int) this.inputText.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC).filter(c -> Character.isLowerCase(c)).count();
        this.uppercaseCountCyrillic = (int) this.inputText.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC).filter(c -> Character.isUpperCase(c)).count();
        this.lowercaseCountLatin = (int) this.inputText.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN).filter(c -> Character.isLowerCase(c)).count();
        this.uppercaseCountLatin = (int) this.inputText.chars().filter(Character::isAlphabetic).filter(c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN).filter(c -> Character.isUpperCase(c)).count();
        this.punctuationMarksCount = (int) this.inputText.chars().filter(c -> ".,!?;:-()\"'«»…".indexOf(c) != -1).count();
        this.spacesCount = (int) this.inputText.chars().filter(Character::isWhitespace).count();

        if (!this.inputText.isBlank()) {
            this.isUpdated = true;
        }
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main program = new Main();

        while (true) {
            System.out.print("\n".repeat(50));

            System.out.print("Текущий текст: %s\n 1. Ввести текст\n 2. Выполнить подсчет\n 3. Вывести результат\n 4. Выход\n".formatted(program.getText()));
            
            String input = "";

            while (!"1".equals(input) && !"2".equals(input) && !"3".equals(input) && !"4".equals(input)) {
                System.out.print("Выберите пункт меню: ");
                input = br.readLine();
            }
            
            switch(input) {
                case "1":
                    String selectInputType = " ";
                    while ("123".indexOf(selectInputType.charAt(0)) == -1)
                    {
                        System.out.print("\n".repeat(50));
                        System.out.print("1. Ввести текст вручную\n2. Ввести случайный текст\n3. Вернуться в меню\nВыберите нужный пункт: ");
                        selectInputType = br.readLine() + " ";
                    }
                    switch(selectInputType.charAt(0)) {
                        case '1':
                            System.out.print("Введите текст: ");
                            program.setText(br.readLine());
                            break;
                        case '2':
                            program.setText(generateRandomString(100));
                            break;
                        case '3':
                            break;
                    }
                    break;
                case "2":
                    program.countParameters();
                    break;
                case "3":
                    System.out.print("\n".repeat(50));
                    if (program.getIsUpdated()) {
                        System.out.print("Строчная кириллица: %d\nПрописная кириллица: %d\nСтрочная латиница: %d\nПрописная латиница: %d\nЗнаков препинания: %d\nПробелов: %d\n".formatted(program.getLowerCyrillic(), program.getUpperCyrillic(), program.getLowerLatin(), program.getUpperLatin(), program.getPunctuationCount(), program.getSpacesCount()));
                    } else {
                        System.out.println("Алгоритм еще не был выполнен, выберите пункт 2 в меню");
                    }
                    System.out.println("Нажмите Enter для продолжения...");
                    br.readLine();
                    break;
                case "4":
                    return;
            }
        }
    }
}
