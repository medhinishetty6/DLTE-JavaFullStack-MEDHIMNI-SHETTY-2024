Transaction: date_of_transaction, amount_in_transaction, to, remarks(Family, Education, Emergency, Bills, Friend)

perform trigger operation

before when insert new transaction with null or empty remarks assign some valid remarks



CREATE OR REPLACE TRIGGER trigger_operation
before insert on transaction for each row
begin
    if :new.transaction_remarks is null then :new.transaction_remarks :='Basic';
    end if;
end;