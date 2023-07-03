## **Midterm Exam Review**

The midterm exam will be in-class / written with one hand-written sheet of notes allowed. Given the 1.5-hour time frame, it is unreasonable to test extensively on most of the programming we've done with pandas/sklearn/matplotlib/etc since having access to the online documentation is vital to being able to work with these tools. Thus, the exam will be focused on the main concepts for the course, not how we interact with our tools in Jupyter notebooks.

### **Topics**

#### **Homework Assignments / Lectures**

- You should review topics discussed in class and found on homework assignments.

#### **Basic Statistics**

- common descriptive stats and their meanings
- working with probability distributions
- common probability distributions (discrete and continuous)

#### **Hypothesis testing**

- What are we trying to show when we perform a hypothesis test?
- What is a P-value and what is its significance?
- What is the "null hypothesis?"

#### **Visualization**

- What are the basic types of plots we work with?
- What is a mark?
- What is a channel? Which channels are the most effective at conveying information to humans?

#### **Linear regression**

- How does linear regression measure the quality of a "line"?
- What are the important statistics that can help us understand the quality of our regression?
- What kinds of models can we fit with ordinary least squares?

#### **Getting data**

- **What's the difference between web scraping/using an API?**

  - Web scraping:

    - Web scraping involves programmatically extracting data from websites by parsing the HTML or XML structure of web pages.
    - It typically involves sending HTTP requests to the web server and then using techniques like parsing, scraping, and extracting specific data elements from the HTML content.
    - Web scraping can be performed on any website, even if it does not provide an API.
    - The extracted data may not be in a structured format, and additional processing may be required to organize and transform the scraped data into a usable format.
    - The legality of web scraping depends on the website's terms of service and the jurisdiction. Some websites explicitly prohibit scraping in their terms of service, while others may have specific guidelines or restrictions.

    Using an API (Application Programming Interface):

    - APIs provide a structured and controlled way to access and retrieve data from web services or online platforms.
    - APIs are interfaces that expose specific endpoints, methods, and parameters through which developers can interact with the underlying system.
    - APIs often provide data in a standardized format such as JSON or XML, making it easier to parse and work with the returned data.
    - Access to APIs is typically regulated through authentication mechanisms, such as API keys or tokens, ensuring that only authorized users or applications can access the data.
    - APIs often have rate limits and usage quotas in place to control the volume of requests and prevent abuse.
    - The data obtained through APIs is usually in a structured format, reducing the need for extensive data parsing or cleaning.


#### **Classification**

- What is logistic regression? How is it related to/different from linear regression?
- What is KNN classification?
- What are the common spatial partitioning data structures used to implement KNN efficiently?
- What are the limitations of logistic regression?
- What are the available knobs to tune with KNN, and what effect to they have on the trained classifier?
  - K
  - distance metric

- What is a decision tree? What are their pros/cons?
- What is bootstrapping?
- What is bagging?
- What are random forests?
- What is cross validation and why is it useful/important?
- What are SVMs and what knobs do we have?

#### **Clustering**

- What's the difference between clustering/classification?
- How does K-means clustering/Lloyd's algorithm work?
- What is hierarchical clustering?
- How does the "bottom up" (agglomerative approach) construct a hierarchical clustering?
- What is a dendrogram and how do you read one?
- What types of distance (dissimilarity) measures might we use for clustering?
- What is a linkage criterion? What are some examples of popular linkage criteria?

#### **Dimensionality Reduction/PCA**

- Why would we want to perform DR?
- What is the geometric interpretation of PCA?
  - finding the directions of maximum variance in a dataset.
- What are the limitations of PCA?