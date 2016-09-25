//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit

class ServiceViewController: UIViewController {

    enum State {
        case loading
        case loaded([ServiceGroup])
        case empty
        case error
    }

    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var collectionView: UICollectionView!
    var departmentId: String?
    var state: State = .loading {
        didSet {
            switch state {
            case .loaded(_):
                collectionView.reloadData()
            default:
                break
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        collectionView.dataSource = self
        collectionView.delegate = self

        activityIndicator.startAnimating()
        FirebaseClient().load(resource: Service.all) { result in
            self.activityIndicator.stopAnimating()
            guard let result = result, let service = result.filter({ $0.uid == self.departmentId }).first else {
                self.state = .error
                return
            }
            self.state = .loaded(service.serviceGroups)
        }
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let service = sender as? ServiceGroup,
            let destination = segue.destination as? QueueInfoViewController,
            segue.identifier == "showQueue" else { return }
        destination.service = service
        destination.title = service.name
        if let selectedIndexes = collectionView.indexPathsForSelectedItems {
            for selectedIndex in selectedIndexes {
                collectionView.deselectItem(at: selectedIndex, animated: true)
            }
        }
    }
}

extension ServiceViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = (collectionView.frame.width - 30) * 0.5
        return CGSize(width: width, height: width)
    }
}

extension ServiceViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        switch state {
        case .loaded(let services):
            performSegue(withIdentifier: "showQueue", sender: services[indexPath.row])
        default:
            return
        }
    }
}

extension ServiceViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch state {
        case .loaded(let services):
            return services.count
        default:
            return 0
        }
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        switch state {
        case .loaded(let services):
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: R.reuseIdentifier.serviceCell, for: indexPath)!
            cell.configure(service: services[indexPath.row])
            return cell
        default:
            return UICollectionViewCell()
        }
    }
}
