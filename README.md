# Testing Wikipedia

## Story

This exercise is going to help you challenge your skills with Selenium on the [Wikipedia](https://en.wikipedia.org/).

## What are you going to learn?

- how to locate objects in the browser
- how to interact with them using Selenium
- getting familiar with how does a complex Selenium test scenario works

## Tasks

1. Create an account at `https://en.wikipedia.org/`.
    - Have a Wikipedia account.

2. Login with the prepared username and password pair to `https://en.wikipedia.org/` page.
    - Manually prepared Wikipedia account.
    - Login succesful.

3. Search for the word `Selenium` on the Wikipedia page and check the results.  Assert that you arrived to the `https://en.wikipedia.org/wiki/Selenium_(software)` page.  Finally navigate back to the previous (base) page.
    - Logged in with an account to the `https://en.wikipedia.org/` page.
    - No assertion fails, the test passes.

4. Search for the word `Framework` on the Wikipedia page, click on the first result page. Assert that you arrived to the result page.
    - Logged in with an account to the `https://en.wikipedia.org/` page.
    - No assertion fails, the test passes.

5. Set the font style to `Comic Neue` via `Language Settings`.
    - Logged in with an account to the `https://en.wikipedia.org/` page.
    - Font-type changed to Comic Neue.

6. Search for the `Selenium` page and find some information about the `Selenium Grid` on the Selenium result page.  Assert that there are some relevant information about Selenium Grid on the result page.
    - Logged in with an account to the `https://en.wikipedia.org/` page.
    - Test passes, relevant information about Selenium Grid found on page.

## General requirements

None

## Hints

- Extract your multiple times used code into private methods, so you can avoid code duplications
- Always keep your webdriver, and the UI synchronized (aka. use waits everywhere where they are justified)
- Type "Selenium software" to the search input field, and you will surely find some information about Grid on some result pages!

## Background materials

- <i class="far fa-exclamation"></i> [Selenium Browser Manipulation](project/curriculum/materials/competencies/selenium-basics/selenium-browser-manipulation.md.html)
- <i class="far fa-exclamation"></i> [Selenium Locators](project/curriculum/materials/competencies/selenium-basics/selenium-locators.md.html)
- <i class="far fa-exclamation"></i> [Selenium Waits and Alerts](project/curriculum/materials/competencies/selenium-basics/selenium-waits-and-alerts.md.html)
