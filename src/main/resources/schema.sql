drop table IF EXISTS LOAN_DATA;

create table LOAN_DATA(
    loanNumber number AUTO_INCREMENT  PRIMARY KEY,
    bankName varchar2(20),
    borrowerName varchar2(20),
    principalAmt number,
    tenure number(4),
    emiAmt number,
    roi number,
    amtToPay number
);

create TABLE PAYMENT_DATA(
    paymentId INT AUTO_INCREMENT  PRIMARY KEY,
    lumpsumAmt number,
    emiMonth number(4),
    loanNumber number(2) REFERENCES LOAN_DATA(loanNumber)
);
