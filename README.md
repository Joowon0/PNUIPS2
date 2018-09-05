# PNUIPS2
Making a market webpage using PostgreSQL JDBC.

## Contents

We want to make PNUIPS(PNU Item Purchase System), where its specifications are as follows

### 1. PNUIPS manages the information of items provided by various sellers and users.

### 2. PNUIPS can be used by registered users and the information of users is given as below.

a. E-mail address (unique, case sensitivity)

b. Family name/first name

c. Date of birth

### 3. Users are classified into Regular, VIP, and VVIP class according to the total amount of purchases.
A new user starts from Regular class. A user of Regular class is upgraded to VIP class when her/his total purchase is equal to or more than 200,000 won. Also VIP class is upgraded to VVIP class when the purchase amount reaches to 500,000 won or more.

### 4. When user’s class is upgraded to VIP or VVIP class, he/she get one discount coupon of 10% or 30% respectively.

### 5. Each item has item’s code (6-digit number), name, price, brand, seller’s code(6-digit number), number of stock and number of sales.

### 6. PNUIPS provides item purchase service with the following UI steps
1) search and select items
2) input number of items to buy
3) click button named “cart” then the page change to cart page
4) choose coupon if available and click “purchase” button
5) PNUIPS stores the purchase information with purchase time.

### 7. The amount of purchases is accumulated to the user’s account.

### 8. Also, PNUIPS provides some functions for administration as below;

a. checking each client’s purchase history

b. checking each seller’s sale history

c. checking each seller’s number of sales

d. Finding top 3 best-selling items in a time interval (t1, t2) in terms of number of sales

e. For a given seller, finding items that he/she does not sell but are among top 10 best-selling of highest-income

f. Finding items whose stocks are less than the total amount in carts.

g. Finding items which are commonly in top 10 best-selling of 20’s and 30’s in terms of number of sales.

 

### 9. Design the schemas for your database in a proper way.

### 10. Implement the functions to input your data to the database.
