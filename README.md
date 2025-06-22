# ExpenseTracker
About : ExpenseTrcaker is an Rest API used for tracking all your Incomes along with the Expenses along with Balance amount and recent spendings.

Technology Used : SpringBoot Framework with Java 17 and Hibernate JPA alomg with Java 8 features like Lambda and Optional Class along with streams.

It has different end points to fetch expenses, add expenses and add income, fetch expenses and form Statistics.

End Points of Expenses:
   /api/expense/save - for adding the Expenses
   /api/expense/all - for fetching all the Expenses
   /api/expense/id -HttpGetMethod - for fetching specific Expense with Id
   /api/expense/update/id - for updating any Expense with given id
   /api/expense/id -HttpDeleteMethod - for deleting any expense with Id.
   
End Points of Income:
   /api/income/post - for adding the Income
   /api/income/all - for fetching all the Income
   /api/income/id -HttpGetMethod - for fetching specific Income with Id
   /api/income/id -HttpPutMethod - for updating any Income with given id
   /api/income/id -HttpDeleteMethod - for deleting any Income with Id
   
End Points of Statistics:  
   /api/stats/from/to- for fetching all the Expenses and Incomes between given date range along with Balance amount, Recent Expenditures,Recent Income
   /api/stats/getstats - for fetching all the Expenses available in database
   
DataBase Used -  Postgresql

It can be implemented with any UI interferences to fetch the data and display accordingly.
   
