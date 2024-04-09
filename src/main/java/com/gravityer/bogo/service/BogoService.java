package com.gravityer.bogo.service;

import com.gravityer.bogo.model.CalculationResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BogoService {

    public CalculationResult calculateBogo(int[] prices, int rule) {
        Arrays.sort(prices);
        List<Integer> discounted = new ArrayList<>();
        List<Integer> payable = new ArrayList<>();

        switch (rule) {
            case 1 -> {
                for (int i = prices.length - 1; i > 0; i--) {
                    payable.add(prices[i]);
                    if (prices[i - 1] <= prices[i]) {
                        discounted.add(prices[i - 1]);
                        i--;  // skip the next item as it's taken as free
                    }
                }
                if (prices.length % 2 != 0) {
                    payable.add(prices[0]);  // Add the last unpaired item
                }
            }
            case 2 -> {
                boolean[] used = new boolean[prices.length];

                for (int i = prices.length - 1; i > 0; i--) {
                    if (!used[i]) {  // Only consider unused items
                        int j = i - 1;
                        while (j >= 0 && prices[j] == prices[i] || used[j]) {  // Skip duplicates of the same highest price
                            j--;
                        }
                        if (j >= 0 && prices[j] < prices[i]) {  // Ensure there is a lesser item to discount
                            payable.add(prices[i]);  // Most expensive item is paid
                            discounted.add(prices[j]);  // Next less expensive item is free
                            used[j] = true;  // Mark the cheaper item as used
                            used[i] = true;  // Mark the more expensive item as used
                        } else {
                            payable.add(prices[i]);  // No lesser item, it must be paid
                            used[i] = true;  // Mark as used
                        }
                    }
                }

                // Handle the last remaining item if it's unused
                if (!used[0]) {
                    payable.add(prices[0]);
                }
            }
            case 3 -> {
                int i = prices.length - 1;
                while (i >= 0) {

                    // pick the topmost item
                    int topItem = prices[i];
                    payable.add(topItem);
                    i--;

                    // proceed if there are items left
                    if (i >= 0) {
                        // pick the next highest priced item only if it's lower than or equal to top picked item
                        if (prices[i] <= topItem) {
                            payable.add(prices[i]);
                            i--;
                        }

                        // proceed if there are items left
                        if (i >= 0) {
                            // pick the next item only if it's strictly less than top picked item
                            if (prices[i] < topItem) {
                                discounted.add(prices[i]);
                                i--;
                            }

                            // proceed if there are items left
                            if (i >= 0) {
                                // pick the next item only if it's strictly less than top picked item
                                if (prices[i] < topItem) {
                                    discounted.add(prices[i]);
                                    i--;
                                }
                            }
                        }
                    }
                }
            }
            default -> throw new IllegalArgumentException("Invalid rule number");
        }

        return new CalculationResult(discounted, payable);
    }
}
