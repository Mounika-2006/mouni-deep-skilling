BEGIN
    FOR cust IN (
        SELECT CustomerID, Age
        FROM Customers
        WHERE Age > 60
    )
    LOOP
        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE CustomerID = cust.CustomerID;

        DBMS_OUTPUT.PUT_LINE(
            'Discount applied for Customer ID: ' || cust.CustomerID
        );
    END LOOP;

    COMMIT;
END;
/