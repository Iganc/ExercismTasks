public class SalaryCalculator {
    public double salaryMultiplier(int daysSkipped) {
        boolean skip = daysSkipped > 5;
        double multiplier = skip ? 0.85 : 1.0;
        return multiplier;
    }

    public int bonusMultiplier(int productsSold) {
        boolean bonus = productsSold > 20;
        int multiplier = bonus ? 10 : 13;
        return multiplier;
    }

    public double bonusForProductsSold(int productsSold) {
        int multiplier = bonusMultiplier(productsSold);
        return productsSold * multiplier;
    }

    public double finalSalary(int daysSkipped, int productsSold) {
        double bonus_money = bonusForProductsSold(productsSold);
        double salary_multiplier = salaryMultiplier(daysSkipped);
        double salary = (1000 + bonus_money) * salary_multiplier;
        boolean cap = salary <= 2000;
        return cap ? salary : 2000;
    } 
}
