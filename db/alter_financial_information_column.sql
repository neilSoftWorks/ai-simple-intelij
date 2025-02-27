-- 1. Add a new VARCHAR2 column
ALTER TABLE business_details
ADD financial_information_varchar VARCHAR2(255);

-- 2. Copy data from CLOB to VARCHAR2 (truncate if needed)
UPDATE business_details
SET financial_information_varchar = SUBSTR(financial_information, 1, 255);

-- 3. Drop the old CLOB column
ALTER TABLE business_details
DROP COLUMN financial_information;

-- 4. Rename the new VARCHAR2 column
ALTER TABLE business_details
RENAME COLUMN financial_information_varchar TO financial_information;