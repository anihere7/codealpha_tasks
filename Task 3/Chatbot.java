import java.util.Scanner;

public class Chatbot {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("        CODEALPHA AI CHATBOT");
        System.out.println("=================================");
        System.out.println("Type 'bye' to exit.");

        while (true) {

            System.out.print("\nYou: ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("bye") || input.equals("exit")) {
                System.out.println("Bot: Goodbye! Have a nice day!");
                break;
            }

            String response = getResponse(input);

            System.out.println("Bot: " + response);
        }

        scanner.close();
    }

    public static String getResponse(String input) {

        if (input.equals("hello") ||
            input.equals("hi") ||
            input.equals("hey")) {

            return "Hello! How can I help you?";
        }

        if (input.contains("name")) {
            return "I am CodeAlpha AI Chatbot.";
        }

        if (input.contains("how are you")) {
            return "I am fine! Thank you for asking.";
        }

        if (input.contains("java")) {
            return "Java is a popular object-oriented programming language.";
        }

        if (input.contains("codealpha")) {
            return "CodeAlpha provides internship opportunities for students.";
        }

        if (input.contains("internship")) {
            return "This chatbot is created as part of a Java internship project.";
        }

        if (input.contains("help")) {
            return "You can ask me about Java, CodeAlpha, internships, or my name.";
        }

        if (input.contains("thank")) {
            return "You're welcome!";
        }

        return "Sorry, I don't understand that. Please try another question.";
    }
}