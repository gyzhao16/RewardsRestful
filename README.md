# RewardsRestful
Restful design project

#### All endpoints
- GET     /api/customer                       get all customer information
- GET     /api/customer/{id}                  get specific customer information
- POST    /api/customer                       create customer
- PUT     /api/customer/{id}                  update customer infomation
- DELETE  /api/customer/{id}                  delete customer
- GET     /api/transactions                   get all transactions
- GET     /api/transactions/{customerId}        get all transactions by one customer
- GET     /api/transaction/{transactionId}    get specific transaction information
- POST    /api/transaction                    create transaction
- PUT     /api/transaction/{transactionId}    update transaction
- DELETE  /api/transaction/{transactionId}    delete transaction
- GET     /api/reward/{customerId}            get customer's total reward
- GET     /api/reward/{customerId}/{month}    get customer's reward in a given period

#### Validation (Jakarta)
- Customer fields
- Transaction fields

#### Exception Handling
- CustomerNotFoundException
- TransactionNotFoundException
- MonthNotValidException

#### Unit tests (JUnit + Mockito)
- CustomerControllerTest
- TransactionControllerTest
- CustomerServiceTest
- TransactionServiceTest
