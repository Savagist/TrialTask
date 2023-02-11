# TrialTask

## API


### User
 - GET - /api/user - method of getting all users
 - GET - /api/user/{id} - method of getting user by id
 - POST - /api/user - method of creating user, request structure:
       {
           "name":"your_name",
           "email":"your_email",
           "password":"your_password"
       }
       
 ### Quote
 - GET - /api/quote/random - method of getting random a quote
 - GET - /api/quote/{id} - method of getting a quote by id
 - GET - /api/quote - method of getting all quotes
 - GET - /api/quote/best - method of getting the top 10 quotes
 - GET - /api/quote/worse - method of getting the 10 worst quotes
 - POST - /api/quote -  method of creating a quote, request structure:
        {
            "content": "savage"
        }
 - PUT - /api/quote/{id} - method for changing the quote, request structure:
        {
            "content": "savage"
        }
 - DELETE - /api/quote/{id} - method of deleting a quote by id

### Vote
 - GET - /api/vote/up/{id} - vote positively for a quote by id
 - GET - /api/vote/down/{id} - vote negatively for a quote by id
