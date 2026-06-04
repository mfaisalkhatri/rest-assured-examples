/*      Copyright 2022 Mohammad Faisal Khatri
        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/

package data.reqres;

import lombok.Getter;
import lombok.Setter;

/**
 * Created By Faisal Khatri on 19-11-2021
 */
@Getter
@Setter
public class PostData {

    private final String user_id;
    private final String product_id;
    private final String product_name;
    private final int    product_amount;
    private final int    qty;
    private final int    tax_amt;
    private final int    total_amt;

    public PostData (final String user_id, final String product_id, final String product_name, final int product_amount,
        final int qty, final int tax_amt, final int total_amt) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_amount = product_amount;
        this.qty = qty;
        this.tax_amt = tax_amt;
        this.total_amt = total_amt;
    }
}