BEGIN
    FOR loan_rec IN (
        SELECT c.CustomerName,
               l.LoanID,
               l.DueDate
        FROM Customers c
        JOIN Loans l
        ON c.CustomerID = l.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE
                            AND SYSDATE + 30
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Dear ' ||
            loan_rec.CustomerName ||
            ', your Loan ID ' ||
            loan_rec.LoanID ||
            ' is due on ' ||
            TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY')
        );
    END LOOP;
END;
/