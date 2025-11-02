IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'AidenBankDB')
BEGIN
    CREATE DATABASE [AidenBankDB];
END
GO

