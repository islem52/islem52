
    public class DateTime {
        private long milliseconds;
    
        public DateTime(long milliseconds) {
            this.milliseconds = milliseconds;
        }
    
        // Getters and setters
        public long getMilliseconds() {
            return milliseconds;
        }
    
        public void setMilliseconds(long milliseconds) {
            this.milliseconds = milliseconds;
        }
        
         // Method to check if a year is a leap year
         private static boolean isLeapYear(int year) {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        }

        // Method to calculate the number of days in a year (accounting for leap years)
        private static long daysInYear(int year) {
            return isLeapYear(year) ? 366 : 365;
        }

         // Method to calculate the number of days in a month (accounting for leap years)
         private static int daysInMonth(int year, int month) {
            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12:
                    return 31;
                case 4, 6, 9, 11:
                    return 30;
                case 2:
                    return isLeapYear(year) ? 29 : 28;
                default:
                    throw new IllegalArgumentException("Invalid month: " + month);
            }
        }
    
        // Method to get actual date and time in specified format
        public static String getActualDateTime() {
            long currentTimeMillis = System.currentTimeMillis();
            long seconds = currentTimeMillis / 1000;
    
            // Calculate seconds since the epoch
            long days = seconds / (24 * 3600);
            int year = 1970;
    
            // Adjust year and remaining days for leap years
            while (days >= daysInYear(year)) {
                days -= daysInYear(year);
                year++;
            }
    
            int month = 1;
            int daysInMonth;
    
            // Adjust month and remaining days for the current year
            while (true) {
                daysInMonth = daysInMonth(year, month);
                if (days < daysInMonth) {
                    break;
                }
                days -= daysInMonth;
                month++;
            }
    
            // Calculate day, hour, minute, second
            int day = (int) (days + 1); // Day is 1-indexed
            long remainingSecondsOfDay = seconds % (24 * 3600);
            int hour = (int) (remainingSecondsOfDay / 3600);
            int minute = (int) ((remainingSecondsOfDay % 3600) / 60);
            int second = (int) (remainingSecondsOfDay % 60);
    
            // Format the date and time
            return String.format("%04d-%02d-%02d :: %02d:%02d:%02d", year, month, day, hour, minute, second);
        }
    
    
       
    }
    

