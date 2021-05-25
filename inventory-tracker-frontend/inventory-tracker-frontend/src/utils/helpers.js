export function formatPricePerUnit(pricePerUnit) {
    return pricePerUnit.toFixed(2);
  }
  
  export function capitalizeEach(string) {
    return string
      .trim()
      .toLowerCase()
      .replace(/\w\S*/g, (w) => w.replace(/^\w/, (c) => c.toUpperCase()));
  }