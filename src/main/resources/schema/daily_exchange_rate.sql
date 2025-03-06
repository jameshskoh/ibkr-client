CREATE TABLE daily_exchange_rate (
    base_currency VARCHAR(3) NOT NULL,
    target_currency VARCHAR(3) NOT NULL,
    date DATE NOT NULL,
    open DECIMAL(15, 5) NOT NULL,
    high DECIMAL(15, 5) NOT NULL,
    low DECIMAL(15, 5) NOT NULL,
    close DECIMAL(15, 5) NOT NULL,
    wap DECIMAL(15, 5) NOT NULL,
    volume DECIMAL(20, 5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (base_currency, target_currency, date)
);
