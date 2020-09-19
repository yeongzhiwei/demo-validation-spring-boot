# Using and extending Hibernate validation in Spring Boot app

A Spring Boot app that demostrates how to use Hibernate validation in varying conditions

- auto validate incoming request body
- validate an object on demand
  - return a custom message
  - throw a custom exception with custom message
- define a custom annotation on top of `@Pattern` or a composite annotation
- create a custom annotation that validates object via codes

## Declaring annotations

- Example: [`Customer`](src/main/java/com/yeongzhiwei/demovalidation/domain/Customer.java)
- `javax.validation.constraints` comes with common annotations e.g. `@NotEmpty` to validate the values
- To define a custom annotation that composes of several annotations or an annotation with fixed elements, see [`NoSpecialCharacters`](src/main/java/com/yeongzhiwei/demovalidation/pattern/NoSpecialCharacters.java)
- To define a custom annotation that validates object using codes, see [`DateTimeString`](src/main/java/com/yeongzhiwei/demovalidation/pattern/DateTimeString.java) and [`DateTimeValidator`](src/main/java/com/yeongzhiwei/demovalidation/pattern/DateTimeValidator.java)

## Enforcing validations

Example: [`ValidationController`](src/main/java/com/yeongzhiwei/demovalidation/ValidationController.java)

Method 1: Annotate the request body with `@Valid` to validate the incoming request DTO on receiving the HTTP request automatically and throw `MethodArgumentNotValidException` if not valid

```JSON
POST /valid
{
    "name": "Alph@"
}

HTTP/1.1 400 BAD REQUEST
{
    "timestamp": "2020-09-18T12:40:38.031+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for object='customer'. Error count: 1",
    "errors": [
        {
            "codes": [
                "Pattern.customer.name",
                "Pattern.name",
                "Pattern.java.lang.String",
                "Pattern"
            ],
            "arguments": [
                {
                    "codes": [
                        "customer.name",
                        "name"
                    ],
                    "arguments": null,
                    "defaultMessage": "name",
                    "code": "name"
                },
                [],
                {
                    "defaultMessage": "^[^;',!@#$%&*()+=|<>?{}\\[\\]~]*$",
                    "codes": [
                        "^[^;',!@#$%&*()+=|<>?{}\\[\\]~]*$"
                    ],
                    "arguments": null
                }
            ],
            "defaultMessage": "must not contain special characters",
            "objectName": "customer",
            "field": "name",
            "rejectedValue": "Alph@",
            "bindingFailure": false,
            "code": "Pattern"
        }
    ],
    "path": "/valid"
}
```

Method 2: Define a Validator (e.g. [`ObjectValidator`](src/main/java/com/yeongzhiwei/demovalidation/ObjectValidator.java)) component to handle the result of the validation and return a custom type or throw a custom exception. Then controller or service can autowire the validator and validate an object on demand

```JSON
POST /validate
{
    "name": "Alph@"
}

HTTP/1.1 400 BAD REQUEST
name (Alph@) must not contain special characters
```

Method 3: Annotate the `Service` (e.g. [`ValidationService`](src/main/java/com/yeongzhiwei/demovalidation/ValidationService.java)) with `@Validated` together with annotating the parameter with `@Valid` to validate the object on method call automatically and throw `javax.validation.ConstraintViolationException` if not valid

```JSON
POST /validated
{
    "name": "Alph@"
}

HTTP/1.1 500 INTERNAL SERVER ERROR
{
    "timestamp":"2020-09-18T12:43:43.035+00:00",
    "status":500,
    "error":"Internal Server Error",
    "message":"validate.customer.name: must not contain special characters",
    "path":"/validated"
}
```
