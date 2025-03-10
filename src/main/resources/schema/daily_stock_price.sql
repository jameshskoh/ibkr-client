CREATE TABLE daily_stock_price (
    symbol VARCHAR(32) NOT NULL,
    exchange VARCHAR(32) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    date DATE NOT NULL,
    open DECIMAL(15, 5) NOT NULL,
    high DECIMAL(15, 5) NOT NULL,
    low DECIMAL(15, 5) NOT NULL,
    close DECIMAL(15, 5) NOT NULL,
    wap DECIMAL(15, 5) NOT NULL,
    volume DECIMAL(20, 5) NOT NULL,
    source VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (symbol, exchange, currency, date)
);
