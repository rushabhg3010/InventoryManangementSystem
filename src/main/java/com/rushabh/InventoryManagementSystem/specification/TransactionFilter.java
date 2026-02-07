package com.rushabh.InventoryManagementSystem.specification;

import com.rushabh.InventoryManagementSystem.models.Transaction;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionFilter {

    public static Specification <Transaction> byFilter(String searchValue){
        return (root, query, criteriaBuilder) -> {
            if (searchValue == null || searchValue.isBlank()){
                return criteriaBuilder.conjunction();
            }

            String searchPattern = "%"+searchValue.toLowerCase()+"%";

            //create a list to hold the predicates
            List<Predicate> predicates = new ArrayList<>();

            //search within transaction fields
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("note")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("status").as(String.class)),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("transactionType").as(String.class)),searchPattern));


            //safely join to check the user fields
            if(root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("user"))){
                root.join("user", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user", JoinType.LEFT).get("name")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user", JoinType.LEFT).get("email")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user", JoinType.LEFT).get("phoneNumber")),searchPattern));

            //safely join to check the supplier/vendor fields
            if(root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("vendor"))){
                root.join("vendor", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("vendor", JoinType.LEFT).get("name")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("vendor", JoinType.LEFT).get("contactInfo")),searchPattern));

            //safely join to check the products fields
            if(root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("product"))){
                root.join("product", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("product", JoinType.LEFT).get("productName")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("product", JoinType.LEFT).get("productSku")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("product", JoinType.LEFT).get("productDescription")),searchPattern));

            //safely join to check the category fields
            if(root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("product"))
                    && root.join("product").getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("category"))
            ){
                root.join("product", JoinType.LEFT).join("category",JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("product", JoinType.LEFT).join("category", JoinType.LEFT).get("name")),searchPattern));

            //combine all predicates with OR
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));

        };

        }

     public static Specification<Transaction> byMonthAndYear(int month, int year ){
        return (root, query, criteriaBuilder) -> {
            Expression<Integer> monthExpression= criteriaBuilder.function("month", Integer.class, root.get("transactionDate"));
            Expression<Integer> yearExpression= criteriaBuilder.function("month", Integer.class, root.get("transactionDate"));

            Predicate monthPredicate = criteriaBuilder.equal(monthExpression,month);
            Predicate yearPredicate = criteriaBuilder.equal(yearExpression,year);

            return criteriaBuilder.and(monthPredicate,yearPredicate);
        };
    }


}
