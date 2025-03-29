# BrowserStackAssignmentWebScraping
This is a assigment for browsersatck demonstrating skills in web scraping, API integration, and text processing using the Selenium framework 

Framework Overview
This is an automation testing framework designed to perform web scraping and content validation on the El País website using Selenium WebDriver in combination with BrowserStack for cross-browser testing across multiple desktop and mobile platforms.

Key Features:
Cross-Browser Testing: Supports multiple browsers (Chrome, Firefox, Safari) on both desktop and mobile platforms.

Mobile Device Testing: Capable of testing on real mobile devices (iPhone, Samsung Galaxy, Google Pixel, etc.) using BrowserStack's real-device cloud infrastructure.

Selenium WebDriver Integration: Uses Selenium WebDriver for automating browser interactions, including accepting consent pop-ups and scraping article data.

Translation & Scraping: Extracts article titles, translates them from Spanish to English using the Rapid Translate API, and performs content validation.

Dynamic Image Downloading: Downloads images associated with scraped articles and saves them in a project directory.

Parallel Test Execution: Utilizes TestNG to run tests in parallel on multiple browsers and devices using configuration from testng.xml.

BrowserStack Integration: Seamlessly integrates with BrowserStack for cloud-based testing of web applications on real devices, supporting a wide range of OS and browser combinations.

Framework Components:
BaseDriver: Initializes WebDriver based on local or BrowserStack execution configurations.

ElPaisPage: Page Object Model class responsible for scraping article content, handling UI interactions, and translating text.

Utils: Utility class for translating text using the Rapid Translate API and downloading images.

ElPaisTest: Test class that combines all actions (scraping, translating, downloading) and validates the functionality across different environments.

Test Configuration:
TestNG: Configured with parallel execution support for testing on multiple browsers and mobile devices.

BrowserStack: Used for cross-browser testing, providing cloud-based real device access.

Execution:
Tests can be run directly from the Test class or through TestNG with configurations provided in the testng.xml file.

Set up BrowserStack credentials via environment variables or hardcoded values.

Supports running tests locally or on the BrowserStack cloud.
