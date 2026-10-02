 // 666//
import pandas as pd 
import matplotlib.pyplot as plt 
from sklearn.linear_model import LinearRegression 
from sklearn.model_selection import train_test_split 
from sklearn.metrics import mean_squared_error, r2_score 
import seaborn as sns 
df = pd.read_csv("student_scores.csv") 
X = df[["Hours"]] 
y = df["Marks"] 
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42) 
model = LinearRegression() 
model.fit(X_train, y_train) 
print("Intercept (a): ", model.intercept_) 
print("Slope (b): ", model.coef_) 
y_pred = model.predict(X_test) 
print("Mean Squared Error: ", mean_squared_error(y_test, y_pred)) 
print("R² Score: ", r2_score(y_test, y_pred)) 
plt.scatter(X, y, color="blue") 
plt.plot(X, model.predict(X), color="red") 
plt.xlabel("Hours of Study") 
plt.ylabel("Marks") 
plt.title("Simple Linear Regression") 
plt.show() 

//77//

import pandas as pd 
from sklearn.linear_model import LogisticRegression 
from sklearn.tree import DecisionTreeClassifier, plot_tree 
from sklearn.metrics import classification_report 
import matplotlib.pyplot as plt 
data = { 
"Hours_Studied": [5,3,8,2,6,1,7,4,9,2], 
"Attendance": [80,60,90,40,85,35,88,65,92,50], 
"Pass": [1,0,1,0,1,0,1,0,1,0] 
} 
df = pd.DataFrame(data) 
df.to_csv("student_performance.csv", index=False) 
print(df) 
X = df[["Hours_Studied", "Attendance"]] 
y = df["Pass"] 
log_reg = LogisticRegression() 
log_reg.fit(X, y) 
y_pred_log = log_reg.predict(X) 
print("Logistic Regression Classification Report: ") 
print(classification_report(y, y_pred_log)) 
tree_clf = DecisionTreeClassifier(max_depth=3, random_state=42) 
tree_clf.fit(X, y) 
y_pred_tree = tree_clf.predict(X) 
print("Decision Tree Classification Report: ") 
print(classification_report(y, y_pred_tree)) 
plt.figure(figsize=(8,6)) 
plot_tree(tree_clf, feature_names=["Hours_Studied", "Attendance"], class_names=["Fail", 
"Pass"], filled=True)
plt.show()

/8/

import pandas as pd 
import matplotlib.pyplot as plt 
import seaborn as sns 
from sklearn.cluster import KMeans 
df = pd.read_csv("customers.csv") 
print(df.head()) 
X = df[['Annual_Income', 'Spending_Score']] 
wcss = [] 
for i in range(1, 11): 
kmeans = KMeans(n_clusters=i, random_state=42) 
kmeans.fit(X) 
wcss.append(kmeans.inertia_) 
plt.plot(range(1, 11), wcss, marker='o') 
plt.title("Elbow Method") 
plt.xlabel("Number of clusters") 
plt.ylabel("WCSS") 
plt.show()

# Step 5: Fit K-Means with Optimal k

kmeans = KMeans(n_clusters=3, random_state=42, n_init=10)

df['Cluster'] = kmeans.fit_predict(X)

print(df.head())


# Step 6: Visualize the Clusters

plt.figure(figsize=(8, 6))

sns.scatterplot(
    x='Annual_Income',
    y='Spending_Score',
    hue='Cluster',
    data=df,
    palette='Set1',
    s=100
)

plt.scatter(
    kmeans.cluster_centers_[:, 0],
    kmeans.cluster_centers_[:, 1],
    s=300,
    c='yellow',
    marker='X',
    label='Centroids'
)

plt.title("Customer Segmentation using K-Means")
plt.xlabel("Annual Income")
plt.ylabel("Spending Score")
plt.legend()

plt.show()

//9//

import pandas as pd 
import matplotlib.pyplot as plt 
from sklearn.preprocessing import StandardScaler 
from sklearn.decomposition import PCA 
df = pd.read_csv("iris_pca_data.csv") 
print("Original Dataset:") 
print(df) 
X = df[['sepal_length', 'sepal_width', 'petal_length', 'petal_width']] 
scaler = StandardScaler() 
X_scaled = scaler.fit_transform(X) 
pca = PCA(n_components=2) 
X_pca = pca.fit_transform(X_scaled) 
pca_df = pd.DataFrame( 
X_pca, 
columns=['Principal_Component_1', 'Principal_Component_2'] 
) 
print("\nDataset After PCA:") 
print(pca_df) 
print("\nExplained Variance Ratio:") 
print(pca.explained_variance_ratio_) 
total_variance = sum(pca.explained_variance_ratio_) * 100 
print("\nTotal Variance Retained:", round(total_variance, 2), "%") 
plt.figure(figsize=(8, 5)) 
plt.scatter( 
X_pca[:, 0], 
X_pca[:, 1], 
color='blue', 
marker='o' 
) 
plt.xlabel("Principal Component 1") 
plt.ylabel("Principal Component 2") 
plt.title("PCA - Dimensionality Reduction") 
plt.grid(True) 
plt.show()


//10//

import pandas as pd 
import matplotlib.pyplot as plt 
from sklearn.preprocessing import StandardScaler 
from sklearn.decomposition import PCA 
df = pd.read_csv("iris_pca_data.csv") 
print("Original Dataset:") 
print(df) 
X = df[['sepal_length', 'sepal_width', 'petal_length', 'petal_width']] 
scaler = StandardScaler() 
X_scaled = scaler.fit_transform(X) 
pca = PCA(n_components=2) 
X_pca = pca.fit_transform(X_scaled) 
pca_df = pd.DataFrame( 
X_pca, 
columns=['Principal_Component_1', 'Principal_Component_2'] 
) 
print("\nDataset After PCA:") 
print(pca_df) 
print("\nExplained Variance Ratio:") 
print(pca.explained_variance_ratio_) 
total_variance = sum(pca.explained_variance_ratio_) * 100 
print("\nTotal Variance Retained:", round(total_variance, 2), "%") 
plt.figure(figsize=(8, 5)) 
plt.scatter( 
X_pca[:, 0], 
X_pca[:, 1], 
color='blue', 
marker='o' 
) 
plt.xlabel("Principal Component 1") 
plt.ylabel("Principal Component 2") 
plt.title("PCA - Dimensionality Reduction") 
plt.grid(True) 
plt.show()