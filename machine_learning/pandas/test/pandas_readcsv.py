import pandas as pd
import matplotlib.pyplot as plt

def test_run():
    df = pd.read_csv("data/test.csv", index_col="ds", parse_dates=True, usecols=['ds','page_score', 'owner_id'])
    print df.ix[1:2, ['page_score', 'owner_id']]
    #print df[1:2]
    print '------'
    df.rename(columns={'ds':'date'})
    print df.head()

def get_col():
    df = pd.read_csv("data/test.csv", index_col="ds", parse_dates=True, usecols=['ds','page_score'])
    print '------'
    print df['page_score'].max()
    ax = df['page_score'].plot(title="my test chart")
    ax.set_xlabel("date")
    ax.set_ylabel("page_score")
    plt.show()

if __name__ == "__main__":
    test_run()
    get_col()
