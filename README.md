# Order Count by Older Products

## Arguments:
- the first and second arguments are dates in the format "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss"
- the third argument and beyond are optional and represent the intervals based on the createdDate of the product. If no intervals are specified, all default intervals will be displayed.
 
## Commands:
- only dates:
  - `java -jar out/artifacts/orders_jar/arorders.jar "2021-01-01" "2024-07-01" `

- passing dates and intervals:
  - `java -jar out/artifacts/orders_jar/arorders.jar "2021-01-01" "2024-07-01" "1-3"`
  - `java -jar out/artifacts/orders_jar/arorders.jar "2021-01-01" "2024-07-01" "1-3" "4-7" "7-12" ">12"`
 