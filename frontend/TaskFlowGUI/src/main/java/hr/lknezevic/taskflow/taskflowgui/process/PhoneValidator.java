package hr.lknezevic.taskflow.taskflowgui.process;

public class PhoneValidator {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.exit(2);
        }

        String phoneNumber = args[0];
        String expectedPrefix = args[1];

        if (expectedPrefix == null) {
            System.out.println("Unknown country code");
            System.exit(3);
        }

        if (phoneNumber.startsWith(expectedPrefix)) {
            System.out.println("OK");
            System.exit(0);
        } else {
            System.out.println("Phone number not valid!");
            System.exit(1);
        }
    }
}
