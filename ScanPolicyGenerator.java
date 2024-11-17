import java.util.Scanner;

public class ScanPolicyGenerator {

    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String UNDERLINE = "\u001B[4m";
    private static final String RESET = "\u001B[0m";

    private static final String[] BITVALUE = {
        "0x00000001", "0x00000002", "0x00000100", "0x00000200", "0x00000400",
        "0x00000800", "0x00001000", "0x00002000", "0x00004000", "0x00010000",
        "0x00020000", "0x00040000", "0x00080000", "0x00100000", "0x00200000",
        "0x00400000", "0x00800000", "0x01000000"
    };

    private static final String[] OPSI = {
        "(bit 0) - OC_SCAN_FILE_SYSTEM_LOCK",
        "(bit 1) - OC_SCAN_DEVICE_LOCK",
        "(bit 8) - OC_SCAN_ALLOW_FS_APFS",
        "(bit 9) - OC_SCAN_ALLOW_FS_HFS",
        "(bit 10) - OC_SCAN_ALLOW_FS_ESP",
        "(bit 11) - OC_SCAN_ALLOW_FS_NTFS",
        "(bit 12) - OC_SCAN_ALLOW_FS_LINUX_ROOT",
        "(bit 13) - OC_SCAN_ALLOW_FS_LINUX_DATA",
        "(bit 14) - OC_SCAN_ALLOW_FS_XBOOTLDR",
        "(bit 16) - OC_SCAN_ALLOW_DEVICE_SATA",
        "(bit 17) - OC_SCAN_ALLOW_DEVICE_SASEX",
        "(bit 18) - OC_SCAN_ALLOW_DEVICE_SCSI",
        "(bit 19) - OC_SCAN_ALLOW_DEVICE_NVME",
        "(bit 20) - OC_SCAN_ALLOW_DEVICE_ATAPI",
        "(bit 21) - OC_SCAN_ALLOW_DEVICE_USB",
        "(bit 22) - OC_SCAN_ALLOW_DEVICE_FIREWIRE",
        "(bit 23) - OC_SCAN_ALLOW_DEVICE_SDCARD",
        "(bit 24) - OC_SCAN_ALLOW_DEVICE_PCI",
        "OpenCore Default: 1.2.3.10.11.12.13.18",
        "Hide all NTFS Drives: 1.2.3.4.10.13.15"
    };

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    private static void coderMark() {
        System.out.println("\n╭━━━╮╱╱╱╱╱╱╱╱╱╱╱╱╱╭━━━┳╮");
        System.out.println("┃╭━━╯╱╱╱╱╱╱╱╱╱╱╱╱╱┃╭━━┫┃" + GREEN);
        System.out.println("┃╰━━┳╮╭┳━┳━━┳━━┳━╮┃╰━━┫┃╭╮╱╭┳━╮╭━╮");
        System.out.println("┃╭━━┫┃┃┃╭┫╭╮┃╭╮┃╭╮┫╭━━┫┃┃┃╱┃┃╭╮┫╭╮╮" + BLUE);
        System.out.println("┃┃╱╱┃╰╯┃┃┃╰╯┃╰╯┃┃┃┃┃╱╱┃╰┫╰━╯┃┃┃┃┃┃┃");
        System.out.println("╰╯╱╱╰━━┻╯╰━╮┣━━┻╯╰┻╯╱╱╰━┻━╮╭┻╯╰┻╯╰╯" + RESET);
        System.out.println("╱╱╱╱╱╱╱╱╱╱╱┃┃╱╱╱╱╱╱╱╱╱╱╱╭━╯┃");
        System.out.println("╱╱╱╱╱╱╱╱╱╱╱╰╯╱╱╱╱╱╱╱╱╱╱╱╰━━╯");
        System.out.println("\n" + UNDERLINE + GREEN + "ScanPolicy OC Generator " + RESET + UNDERLINE + "v0.1\n" + RESET);
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void help() {
        System.out.printf("""
        )> 0x00000001 (bit 0) - OC_SCAN_FILE_SYSTEM_LOCK
           - restricts scanning to only known file systems defined as a part of this policy.
        )> 0x00000002 (bit 1) - OC_SCAN_DEVICE_LOCK
           - restricts scanning to only known device types defined as a part of this policy.
        )> 0x00000100 (bit 8) - OC_SCAN_ALLOW_FS_APFS
           - allows scanning of APFS file system.
        )> 0x00000200 (bit 9) - OC_SCAN_ALLOW_FS_HFS
           - allows scanning of HFS file system.
        )> 0x00000400 (bit 10) - OC_SCAN_ALLOW_FS_ESP
           - allows scanning of EFI System Partition file system.
        )> 0x00000800 (bit 11) - OC_SCAN_ALLOW_FS_NTFS
           - allows scanning of NTFS (Msft Basic Data) file system.
        )> 0x00001000 (bit 12) - OC_SCAN_ALLOW_FS_LINUX_ROOT
           - allows scanning of Linux Root file systems.
        )> 0x00002000 (bit 13) - OC_SCAN_ALLOW_FS_LINUX_DATA
           - allows scanning of Linux Data file systems.
        )> 0x00004000 (bit 14) - OC_SCAN_ALLOW_FS_XBOOTLDR 
           - allows scanning the Extended Boot Loader Partition as defined by the Boot Loader Specification.
        )> 0x00010000 (bit 16) - OC_SCAN_ALLOW_DEVICE_SATA
           - allow scanning SATA devices.
        )> 0x00020000 (bit 17) - OC_SCAN_ALLOW_DEVICE_SASEX
           - allow scanning SAS and Mac NVMe devices.
        )> 0x00040000 (bit 18) - OC_SCAN_ALLOW_DEVICE_SCSI
           - allow scanning SCSI devices.
        )> 0x00080000 (bit 19) - OC_SCAN_ALLOW_DEVICE_NVME 
           - allow scanning NVMe devices.
        )> 0x00100000 (bit 20) - OC_SCAN_ALLOW_DEVICE_ATAPI 
           - allow scanning CD/DVD devices and old SATA.
        )> 0x00200000 (bit 21) - OC_SCAN_ALLOW_DEVICE_USB
           - allow scanning USB devices.
        )> 0x00400000 (bit 22) - OC_SCAN_ALLOW_DEVICE_FIREWIRE
           - allow scanning FireWire devices.
        )> 0x00800000 (bit 23) - OC_SCAN_ALLOW_DEVICE_SDCARD
           - allow scanning card reader devices.
        )> 0x01000000 (bit 24) - OC_SCAN_ALLOW_DEVICE_PCI
           - allow scanning devices directly connected to PCI bus (e.g. VIRTIO).
        """);
    }

    private static void mainMenu() {
        coderMark();
        System.out.println(YELLOW + ")> " + RESET + "1. " + GREEN + "Generate" + RESET);
        System.out.println(YELLOW + ")> " + RESET + "2. " + BLUE + "Help " + RESET + "(description)");
        System.out.println(YELLOW + ")> " + RESET + "3. " + RED + "Exit\n" + RESET);
        System.out.print("Choose an option: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                clearScreen();
                coderMark();
                generateScanPolicy();
                break;
            case "2":
                help();
                continueOrExit();
                break;
            case "3":
                clearScreen();
                coderMark();
                System.out.println(YELLOW + "Thanks for using this script!" + RESET);
                System.exit(0);
            default:
                System.out.println(RED + "Invalid option. Please try again." + RESET);
                mainMenu();
        }
    }

    private static void generateScanPolicy() {
        System.out.println("\nAvailable options: \n");
        System.out.println(YELLOW + ")> " + RESET + "1. " + GREEN + "OpenCore Default" + RESET);
        System.out.println(YELLOW + ")> " + RESET + "2. " + GREEN + "Hide all NTFS Drives" + RESET);
        System.out.println(YELLOW + ")> " + RESET + "3. " + YELLOW + "Custom" + RESET);
        System.out.print("\nEnter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println(YELLOW + "\nOpenCore Default: \n" + RESET);
                System.out.println(UNDERLINE + "ScanPolicy (Integer) :" + RESET + GREEN + " " + "17760515" + RESET);
                System.out.println(UNDERLINE + "ScanPolicy (in hex)  :" + RESET + GREEN + " " + "0x10F0103" + RESET);
                continueOrExit();
                break;
            case "2":
                System.out.println(YELLOW + "\nHide all NTFS Drives: \n" + RESET);
                System.out.println(UNDERLINE + "ScanPolicy (Integer) :" + RESET + GREEN + " " + "2687747" + RESET);
                System.out.println(UNDERLINE + "ScanPolicy (in hex)  :" + RESET + GREEN + " " + "0x290303" + RESET);
                continueOrExit();
                break;
            case "3":
                clearScreen();
                coderMark();
                System.out.println("\nAvailable options: \n");
                for (int i = 0; i < OPSI.length; i++) {
                    System.out.println(YELLOW + (i + 1) + "." + RESET + " " + OPSI[i]);
                }
                System.out.println(YELLOW + "\n(e.g., Separate with (.) ~ 1.3 for options 1 and 3)" + RESET);
                System.out.print("\nEnter your choices: ");
                String input = scanner.nextLine();
                String[] choices = input.split("\\.");
                int scanPolicy = 0;

                for (String c : choices) {
                    try {
                        int index = Integer.parseInt(c) - 1;
                        if (index >= 0 && index < BITVALUE.length) {
                            scanPolicy |= Integer.parseInt(BITVALUE[index].substring(2), 16);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid input. Please enter numbers only." + RESET);
                        generateScanPolicy();
                        return;
                    }
                    if (Integer.parseInt(c) < 1 || Integer.parseInt(c) > 20) {
                        System.out.println(RED + "Invalid input. Please enter a number between 1 and 18." + RESET);
                        generateScanPolicy();
                        return;
                    }
                    if (Integer.parseInt(c) == 19 || Integer.parseInt(c) == 20) {
                        System.out.println(RED + "Invalid input. 19 or 20? Ohh dude,Really!,hmm..." + RESET);
                        generateScanPolicy();
                        return;
                    }
                }

                System.out.println(YELLOW + "\nResult: \n" + RESET);
                System.out.println(UNDERLINE + "ScanPolicy (Integer) :" + RESET + GREEN + " " + scanPolicy + RESET);
                System.out.println(UNDERLINE + "ScanPolicy (in hex)  :" + RESET + GREEN + " 0x" + Integer.toHexString(scanPolicy).toUpperCase() + "\n" + RESET);
                continueOrExit();
                break;
            default:
                System.out.println(RED + "Invalid option. Please try again." + RESET);
                generateScanPolicy();
        }
    }

    private static void continueOrExit() {
        System.out.print(UNDERLINE + GREEN + "\nDo you want to continue using the script?" + RESET + " (y/n): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            clearScreen();
            mainMenu();
        } else {
            clearScreen();
            coderMark();
            System.out.println(YELLOW + "Thanks for using this script!" + RESET);
            System.exit(0);
        }
    }
}

