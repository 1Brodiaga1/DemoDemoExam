// MaterialCalculator.java
package util;

public class MaterialCalculator {

    /**
     * Рассчитывает количество материала, необходимого для производства продукции
     *
     * @param productTypeId Идентификатор типа продукции
     * @param materialTypeId Идентификатор типа материала
     * @param productQuantity Количество получаемой продукции
     * @param param1 Первый параметр продукции (вещественное, положительное)
     * @param param2 Второй параметр продукции (вещественное, положительное)
     * @return Целое число - количество необходимого материала с учетом возможного брака
     */
    public static int calculateRequiredMaterial(int productTypeId, int materialTypeId,
                                                int productQuantity, double param1, double param2) {
        // Проверка входных параметров
        if (productQuantity <= 0 || param1 <= 0 || param2 <= 0) {
            throw new IllegalArgumentException("Количество продукции и параметры должны быть положительными числами");
        }

        // Базовое количество материала на единицу продукции
        double baseAmount = getBaseMaterialAmount(productTypeId, materialTypeId);

        // Коэффициент, зависящий от параметров продукции
        double paramCoefficient = calculateParamCoefficient(productTypeId, param1, param2);

        // Процент брака для данного типа материала
        double defectRate = getMaterialDefectRate(materialTypeId);

        // Расчет необходимого количества материала с учетом брака
        double requiredAmount = productQuantity * baseAmount * paramCoefficient;
        double totalWithDefects = requiredAmount * (1 + defectRate);

        // Округление до целого числа в большую сторону
        return (int) Math.ceil(totalWithDefects);
    }

    /**
     * Возвращает базовое количество материала на единицу продукции
     */
    private static double getBaseMaterialAmount(int productTypeId, int materialTypeId) {

        switch (productTypeId) {
            case 1: // Например, мелкая продукция
                return 2.5;
            case 2: // Средняя продукция
                return 5.0;
            case 3: // Крупная продукция
                return 10.0;
            default:
                return 1.0;
        }
    }

    /**
     * Рассчитывает коэффициент на основе параметров продукции
     */
    private static double calculateParamCoefficient(int productTypeId, double param1, double param2) {
        // Пример расчета коэффициента в зависимости от параметров и типа продукции
        switch (productTypeId) {
            case 1: // Для первого типа важнее первый параметр
                return 0.5 + (0.1 * param1) + (0.05 * param2);
            case 2: // Для второго типа важны оба параметра
                return 0.3 + (0.08 * param1) + (0.08 * param2);
            case 3: // Для третьего типа важнее второй параметр
                return 0.4 + (0.05 * param1) + (0.1 * param2);
            default:
                return 1.0 + (0.05 * param1) + (0.05 * param2);
        }
    }

    /**
     * Возвращает процент брака для данного типа материала
     */
    private static double getMaterialDefectRate(int materialTypeId) {

        // Пример: разные материалы имеют разный процент брака
        switch (materialTypeId) {
            case 1: // Материал высокого качества
                return 0.05; // 5% брака
            case 2: // Материал среднего качества
                return 0.10; // 10% брака
            case 3: // Материал низкого качества
                return 0.15; // 15% брака
            default:
                return 0.08; // 8% брака по умолчанию
        }
    }
}