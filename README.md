# About this project:

This automation created to do visual comparison.
In this project, you will find a structured approach to visual testing, which includes capturing screenshots for comparison and reporting discrepancies. The chaining page object design pattern allows for a more fluent and readable test code, enabling seamless transitions between different pages of the application.


Diagram of this project:
![alt text](https://github.com/AryawanWijaya//VisualComparisonPlaywrightJava/blob/master/Chaining_page_object_design.jpg?raw=true)

## Dependencies

On this project I used TestNG framework that combined with playwright, this is the detail of dependencies that I used:

- TestNG 7.10.2
- Playwright 1.47.0
- ExtentReport 5.1.2
- Image comparison 4.4.0

## How to run this project

1. Clone this project and build make sure all dependencies downloaded correctly
2. If you want to run visual comparison you can run **testngVisualComparison.xml**
3. If you want to adjust threshold of visual comparison you can change this **image-comparison-threshold** on **config.properties**
> this threshold based on percentage of tolerance, 0 meaning there is no tolerance of visual comparison / exactly same. if you set to 50 meaning there is a 50% tolerance different between expected image and current web application 
4. If you want to run test that implement **Chaining page object design pattern** you can run **testngE2E.xml**

## Result of test:
More detail you can read this [article](https://www.linkedin.com/pulse/setting-up-selenium-grid-4-appium-2-nikolaus-aryawan-ravato-wijaya-ca87c/)

## Result of test:

![alt text](https://github.com/AryawanWijaya//VisualComparisonPlaywrightJava/blob/master/e2eJourneyReport.png?raw=true)
![alt text](https://github.com/AryawanWijaya//VisualComparisonPlaywrightJava/blob/master/failedVisualComparison.png?raw=true)
![alt text](https://github.com/AryawanWijaya//VisualComparisonPlaywrightJava/blob/master/failedVisualComparison2.png?raw=true)
