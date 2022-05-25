public static void sub_Scroll() {

        try {
            JavascriptExecutor js_1 = (JavascriptExecutor) driver;

            // Launch the application
            Thread.sleep(2000);
            //This will scroll the web page till end.
            js_1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
