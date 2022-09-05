[![Java CI with Maven](https://github.com/mfaisalkhatri/rest-assured-examples/actions/workflows/maven.yml/badge.svg)](https://github.com/mfaisalkhatri/rest-assured-examples/actions/workflows/maven.yml)
![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Don't forget to give a :star: to make the project popular.

## :question: What is this Repository about?

This project is the outcome of my self-learning about the API Testing Automation framework - Rest-assured.
I heard a lot about Rest-Assured and how it made the QA's life easier by helping them to run all the tedious API tests
in an efficient way.
Hence, I started learning about the framework and have documented all my learnings in this repository with all 
example code from writing basic tests to running end to end API automation tests.

## :briefcase: What does this repo contain?
- This repo contains example codes of API Tests using Rest-Assured.
- `Hamcrest Matchers` are used for assertions.
- [TestNG][testng_website] Listeners are used to capture the events in logs.
- `Log4j` is used to capture logs.
- [Lombok][Lombok_website] has been used to generate Getter and Setters automatically for post body requests.
- FAKE Rest APIs on [Reqres.in][reqreswebsite] has been used for testing.
- End to End scenarios have been added for the [restful booker APIs][restfulbooker].

## :hammer_and_wrench: Talking more about the Scenarios Covered in this project:
You will get the answers to the following questions and its respective working code example with rest-assured 
framework in this repository:
- How to write tests for `Get` requests?
- How to write tests for `POST` requests?
- How to write tests for `PUT` requests?
- How to write tests for `PATCH` requests?
- How to write tests for `DELETE` requests?
- How to handle the `authentication` requests?
- How to write tests for `SOAP API` requests?
- How to verify the Response Body?
- How to verify the Response Status Code?
- How to verify the Response headers?
- How to extract value from Response Body?
- How to perform assertions using `Hamcrest Matchers`?
- How to create `POJO` for passing values to request body?
- How to use `Lombok` to generate `Getters` and `Setters`?
- How to use `Lombok` for writing the builder pattern code?
- How to use Builder Pattern for test data generation using [Java Faker][java_faker]?
- How to write end-to-end api tests?
- How to perform `JSON Schema Validation`?

## :writing_hand: Blog Links
- [What is API Testing?][blog_apitesting]
- [End to End API testing using rest-assured][e2eblog]
- [How to perform JSON Schema Validation using Rest-Assured?][jsconschemavalidationblog]
- [API Testing using RestAssured and OkHttp][blog_restassured_okhttp]

## :question: Need Assistance?

- Discuss your queries by writing to me @ `mohammadfaisalkhatri@gmail.com`
  OR ping me on any of the social media sites using the below link:
   - [Linktree][linktree]

## :computer: Paid Trainings

Contact me for Paid trainings related to Test Automation and Software Testing, 
mail me @ `mohammadfaisalkhatri@gmail.com` or ping me on [LinkedIn][linkedin]

## :thought_balloon: Checkout the blogs related to Testing on my [website][]


[linkedin]: https://www.linkedin.com/in/faisalkhatri/
[linktree]: https://linktr.ee/faisalkhatri
[website]: https://mfaisalkhatri.github.io
[reqreswebsite]:https://reqres.in/
[blog_restassured_okhttp]: https://mfaisalkhatri.github.io/2020/05/29/restassuredokhttp/
[blog_apitesting]: https://mfaisalkhatri.github.io/2020/08/08/apitesting/
[restfulbooker]:https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-PartialUpdateBooking
[e2eblog]: https://medium.com/@iamfaisalkhatri/end-to-end-api-testing-using-rest-assured-a58c4ea80255
[jsconschemavalidationblog]: https://medium.com/@iamfaisalkhatri/how-to-perform-json-schema-validation-using-rest-assured-64c3b6616a91
[Lombok_website]:https://projectlombok.org/
[testng_website]:https://testng.org
[java_faker]: https://github.com/DiUS/java-faker